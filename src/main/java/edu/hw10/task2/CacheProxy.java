package edu.hw10.task2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object device;
    private final Path cacheDirectory;
    private final Map<List<Object>, Object> cache;

    CacheProxy(Object device) throws IOException {
        this.device = device;
        this.cache = new HashMap<>();
        cacheDirectory = Files.createTempDirectory(device.getClass().getName());
    }

    public static <T> T cache(T object) throws IOException {
        return (T) Proxy.newProxyInstance(
            ClassLoader.getSystemClassLoader(),
            object.getClass().getInterfaces(),
            new CacheProxy(object)
        );
    }

    private static List<Object> toKey(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method.getName());
        if (args != null) {
            key.addAll(Arrays.asList(args));
        }
        return key;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        Cache cacheAnnotation = null;

        for (Method deviceMethod : device.getClass().getMethods()) {
            if (deviceMethod.getName().equals(method.getName())
                && Arrays.equals(
                    deviceMethod.getParameterTypes(),
                    method.getParameterTypes()
                )) {
                cacheAnnotation = deviceMethod.getAnnotation(Cache.class);
                if (cacheAnnotation != null) {
                    break;
                }
            }
        }
        Object answer = null;
        if (cacheAnnotation == null) {
            answer = method.invoke(device, args);
        } else {
            List<Object> key = toKey(method, args);
            if (cacheAnnotation.persist()) {
                Path filePath = Path.of(cacheDirectory.toString(), key.toString());
                if (Files.exists(filePath)) {
                    try (InputStream inputStream = Files.newInputStream(filePath);
                         ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
                    ) {
                        answer = objectInputStream.readObject();
                    }
                } else {
                    Files.createFile(filePath);
                    try (OutputStream outputStream = Files.newOutputStream(filePath);
                         ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                        answer = method.invoke(device, args);
                        objectOutputStream.writeObject(answer);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                if (cache.containsKey(key)) {
                    return cache.get(key);
                } else {
                    try {
                        answer = method.invoke(device, args);
                        cache.put(key, answer);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return answer;

    }
}

