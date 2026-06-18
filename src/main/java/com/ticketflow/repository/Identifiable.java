package com.ticketflow.repository;
//this interface is used to identify entities that have an ID. It is used in the InMemoryRepository class to identify entities that have an ID. It is a generic interface that takes a type parameter ID, which is the type of the ID of the entity.
//
//The interface has a single method, getId(), which returns the ID of the entity.
public interface Identifiable<ID> {
    ID getId();

}

