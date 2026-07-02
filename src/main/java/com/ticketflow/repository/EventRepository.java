package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.Event;

@Repository
public class EventRepository extends InMemoryRepository<Event, Long> {
    // inherits findById, findAll, save, deleteById
}