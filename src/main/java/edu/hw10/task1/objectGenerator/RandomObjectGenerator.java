package edu.hw10.task1.objectGenerator;

import edu.hw10.task1.objectGenerator.baseGenerators.RandomCharGenerator;
import edu.hw10.task1.objectGenerator.baseGenerators.RandomIntGenerator;
import edu.hw10.task1.objectGenerator.baseGenerators.RandomIntegerGenerator;
import edu.hw10.task1.objectGenerator.baseGenerators.RandomParamGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.function.Function;

public class RandomObjectGenerator {
    private RandomObjectGenerator() {
    }

    private static final RandomParamGenerator GENERATOR_POOL;
    private static final Function<Constructor<?>[], Constructor<?>> DEFAULT_CONSTRUCTOR =
        (constructors) -> constructors[0];
    private static final Function<Method[], Method> DEFAULT_METHOD =
        (methods) -> methods[0];

    static {
        GENERATOR_POOL = new RandomIntGenerator();
        GENERATOR_POOL
            .setNext(new RandomIntegerGenerator())
            .setNext(new RandomCharGenerator());
    }

    public static <T> T nextObject(Class<T> object, String methodName)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = DEFAULT_CONSTRUCTOR.apply(object.getConstructors());
        Method createMethod =
            DEFAULT_METHOD.apply(Arrays.stream(object.getMethods())
                .filter(method -> method.getName().equals(methodName)).toArray(Method[]::new));
        Parameter[] params = createMethod.getParameters();
        Object[] paramsValue = new Object[params.length];
        for (int i = 0; i < params.length; ++i) {
            RandomParamGenerator generator = GENERATOR_POOL.chooseGenerator(params[i]);
            paramsValue[i] = generator.generate();
        }
        return (T) createMethod.invoke(object, paramsValue);
    }

    public static <T> T nextObject(Class<T> object)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = DEFAULT_CONSTRUCTOR.apply(object.getConstructors());
        Parameter[] params = constructor.getParameters();
        Object[] paramsValue = new Object[params.length];
        for (int i = 0; i < params.length; ++i) {
            RandomParamGenerator generator = GENERATOR_POOL.chooseGenerator(params[i]);
            paramsValue[i] = generator.generate();

        }
        return (T) constructor.newInstance(paramsValue);
    }
}
