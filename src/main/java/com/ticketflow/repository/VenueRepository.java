package com.ticketflow.repository;

import com.ticketflow.domain.Venue;

public class VenueRepository extends InMemoryRepository<Venue, Long> {
    // inherits findById, findAll, save, deleteById
}