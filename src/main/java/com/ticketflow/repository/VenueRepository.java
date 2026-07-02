package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.Venue;

@Repository
public class VenueRepository extends InMemoryRepository<Venue, Long> {
    // inherits findById, findAll, save, deleteById
}