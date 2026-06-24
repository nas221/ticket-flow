package com.ticketflow.repository;

import com.ticketflow.domain.Event;

public class EventRepository extends InMemoryRepository<Event, Long> {
    // inherits findById, findAll, save, deleteById
}