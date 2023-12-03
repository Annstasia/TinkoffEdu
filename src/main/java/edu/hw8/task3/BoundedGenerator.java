package edu.hw8.task3;

public class BoundedGenerator implements PasswordGenerator {
    boolean shutdown = false;
    String bound;
    UnboundedGenerator generator;

    public BoundedGenerator(UnboundedGenerator generator, String bound) {
        this.generator = generator;
        this.bound = bound;
    }

    @Override
    public String nextPassword() {
        if (shutdown) {
            return null;
        } else {
            String password = generator.nextPassword();
            if (password.equals(bound)) {
                shutdown = true;
                return null;
            }
            return password;
        }
    }
}
