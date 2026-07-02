package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.Seat;

@Repository
public class SeatRepository extends InMemoryRepository<Seat, Long> {
    // inherits findById, findAll, save, deleteById
}