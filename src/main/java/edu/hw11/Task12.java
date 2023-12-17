package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class Task12 {
    private Task12() {
    }

    private final static Logger LOGGER = LogManager.getLogger();
    private static final int A = 2;
    private static final int B = 3;
    private static final int SUM = 5;
    private static final int MULTIPLY = 6;

    public static void task1()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object customClassObject = new ByteBuddy()
            .subclass(Object.class)
            .name("HelloWorldClass")
            .method(named("toString")).intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(Task12.class.getClassLoader())
            .getLoaded().getConstructor().newInstance();
        LOGGER.info(customClassObject.toString());
    }

    public static void task2()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.ArithmeticUtils")
            .resolve();
        DynamicType.Loaded<Object> loaded = new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(named("sum")).intercept(MethodDelegation.to(CustomArithmeticUtils.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        ArithmeticUtils utils = new ArithmeticUtils();
        LOGGER.info(utils.sum(A, B));
    }
}
