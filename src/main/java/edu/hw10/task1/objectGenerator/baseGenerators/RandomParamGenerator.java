package edu.hw10.task1.objectGenerator.baseGenerators;

import java.lang.reflect.Parameter;

public abstract class RandomParamGenerator {
    private RandomParamGenerator next;

    public RandomParamGenerator getNext() {
        return next;
    }

    public RandomParamGenerator setNext(RandomParamGenerator next) {
        this.next = next;
        return next;
    }

    public abstract Object generate();

    abstract boolean acceptParam(Parameter parameter);

    public RandomParamGenerator chooseGenerator(Parameter parameter) {
        if (acceptParam(parameter)) {
            generatorSetUp(parameter);
            return this;
        }
        if (next == null) {
            return null;
        }
        return next.chooseGenerator(parameter);
    }

    void generatorSetUp(Parameter parameter) {
    }
}
