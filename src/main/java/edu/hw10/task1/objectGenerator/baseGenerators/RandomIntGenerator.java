package edu.hw10.task1.objectGenerator.baseGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntGenerator extends RandomParamGenerator {
    private static final Class<?> GENERATION_TYPE = int.class;
    int origin = Integer.MIN_VALUE;
    int bound = Integer.MAX_VALUE;

    @Override
    public Integer generate() {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    @Override
    boolean acceptParam(Parameter parameter) {
        return GENERATION_TYPE.equals(parameter.getType());
    }

    @Override
    void generatorSetUp(Parameter parameter) {
        Min minAnnotation = parameter.getAnnotation(Min.class);
        if (minAnnotation != null) {
            origin = (int) minAnnotation.value();
        } else {
            origin = Integer.MIN_VALUE;
        }
        Max maxAnnotation = parameter.getAnnotation(Max.class);
        if (maxAnnotation != null) {
            bound = (int) maxAnnotation.value();
        } else {
            bound = Integer.MAX_VALUE;
        }

    }
}
