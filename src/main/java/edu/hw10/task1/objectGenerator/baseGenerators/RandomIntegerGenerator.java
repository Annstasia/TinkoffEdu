package edu.hw10.task1.objectGenerator.baseGenerators;

import edu.hw10.task1.annotations.NotNull;
import java.lang.reflect.Parameter;
import java.util.function.Supplier;

public class RandomIntegerGenerator extends RandomParamGenerator {
    private static final int DEFAULT_NOTNULL_PER_NULL = 10;
    boolean notNull = true;
    RandomIntGenerator intGenerator = new RandomIntGenerator();
//    private Supplier<Boolean> nullStrategy = () ->
//            ThreadLocalRandom.current().nextInt(DEFAULT_NOTNULL_PER_NULL) == 0;

    // Сверху стратегия адекватнее, но на такой лучше видно, что null генерируется
    private Supplier<Boolean> nullStrategy = () -> true;

    @Override
    public Integer generate() {
        if (!notNull && nullStrategy.get()) {
            return null;
        }
        return intGenerator.generate();
    }

    @Override
    boolean acceptParam(Parameter parameter) {
        return parameter.getType().equals(Integer.class);
    }

    @Override
    void generatorSetUp(Parameter parameter) {
        intGenerator.generatorSetUp(parameter);
        NotNull notNullAnnotation = parameter.getAnnotation(NotNull.class);
        notNull = (notNullAnnotation != null);
    }

    public void setNullGenerationStrategy(Supplier<Boolean> strategy) {
        nullStrategy = strategy;
    }
}
