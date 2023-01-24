package implementations.generator;

import interfaces.EntityIdGenerator;

public class SimpleGenerator implements EntityIdGenerator {
    Long id = 0L;

    @Override
    public Long generate() {
        return ++id;
    }
}
