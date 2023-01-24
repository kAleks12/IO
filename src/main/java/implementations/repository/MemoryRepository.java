package implementations.repository;

import exceptions.APIException;
import interfaces.Entity;
import interfaces.EntityIdGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

public class MemoryRepository<T extends Entity> {

    protected final Map<Long, T> entities;
    protected final EntityIdGenerator idGenerator;

    public MemoryRepository(EntityIdGenerator idGenerator) {

        this.idGenerator = idGenerator;
        this.entities = new HashMap<>();
    }

    public Optional<T> findById(Long id) {

        return Optional.ofNullable(entities.get(id));
    }

    public void save(T entity) {

        if (isNull(entity.getId())) {

            entity.setId(idGenerator.generate());
        }

        entities.put(entity.getId(), entity);
    }

    public void deleteById(Long id) {
        entities.remove(id);
    }

    public List<T> getAll() {

        return entities.values()
                .stream()
                .toList();
    }
}