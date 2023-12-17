package edu.hw10.task1.objectGenerator.baseGenerators;

import java.lang.reflect.Parameter;

public class RandomCharGenerator extends RandomParamGenerator {
    RandomIntGenerator intGenerator = new RandomIntGenerator();

    @Override
    boolean acceptParam(Parameter parameter) {
        return parameter.getType().equals(char.class);
    }

    @Override
    public Character generate() {
        return (char) (intGenerator.generate() % Character.MAX_VALUE);
    }

    @Override
    void generatorSetUp(Parameter parameter) {
        intGenerator.generatorSetUp(parameter);
    }
}
