package com.ticketflow.repository;
import java.util.List;
import java.util.Optional;
//Blueprint of all the methods that will be implemented in the repository classes
//the repository classes are responsible for the data access layer of the application
public interface Repository<T, ID> {
    Optional<T> findByID(ID id);
    List<T> findAll();
    T save(T entity);
    void deleteById(ID id);

}
