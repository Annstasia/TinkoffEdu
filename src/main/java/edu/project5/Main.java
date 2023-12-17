package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.util.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class Main {
    private final static boolean SHOULD_FAIL_ON_ERROR = true;
    private final static boolean SHOULD_DO_GC = true;
    private final static int FORKS_CNT = 1;
    private final static int WARMUP_FORKS_CNT = 1;
    private final static int WARMUP_ITERATIONS_CNT = 1;
    private final static TimeValue WARMUP_TIME = TimeValue.seconds(10);
    private final static int MEASUREMENT_ITERS_CNT = 2;
    private final static TimeValue TIME_PER_ITERATION = TimeValue.seconds(60);
    private Student student;
    private Method method;
    private MethodHandle handle;
    private CallSite factory;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(Main.class.getSimpleName())
            .shouldFailOnError(SHOULD_FAIL_ON_ERROR)
            .shouldDoGC(SHOULD_DO_GC)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(FORKS_CNT)
            .warmupForks(WARMUP_FORKS_CNT)
            .warmupIterations(WARMUP_ITERATIONS_CNT)
            .warmupTime(WARMUP_TIME)
            .measurementIterations(MEASUREMENT_ITERS_CNT)
            .measurementTime(TIME_PER_ITERATION)
            .build();
        new Runner(options).run();
    }

    @Setup
    public void setup() throws NoSuchMethodException, IllegalAccessException, LambdaConversionException {
        student = new Student("Alexander", "Biryukov");
        method = student.getClass().getMethod("name");
        MethodHandles.Lookup caller = MethodHandles.lookup();
        handle = caller.unreflect(method);
        factory = LambdaMetafactory.metafactory(
            caller,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            handle,
            MethodType.methodType(String.class)
        );
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandle(Blackhole bh) throws Throwable {
        String name = (String) handle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetaFactory(Blackhole bh) throws Throwable {
        String name = ((Supplier<String>) factory.getTarget().invoke(student)).get();
        bh.consume(name);
    }

    record Student(String name, String surname) {
    }

}
