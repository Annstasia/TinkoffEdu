package edu.hw8.task3;

public class GeneratorManager {
    UnboundedGenerator generator = new UnboundedGenerator();
    String last = "";

    String delta = "aaa";

    public synchronized PasswordGenerator getGenerator() {
        String next = generator.nextPassword() + delta;
        PasswordGenerator generatorToReturn = new BoundedGenerator(new UnboundedGenerator(last), next);
        last = next;
        return generatorToReturn;
    }
}
