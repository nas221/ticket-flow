package com.ticketflow.repository;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class InMemoryRepository<T extends Identifiable<ID>, ID> implements Repository<T, ID> {
    private ConcurrentHashMap<ID, T> storage = new ConcurrentHashMap<>();
//Overrides the findByID method from the Repository interface. It takes an ID as a parameter and returns
// an Optional containing the entity with the given ID if it exists in the storage, or an empty Optional if it does not exist.
    @Override
    public Optional<T> findByID(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T save(T entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }


}
