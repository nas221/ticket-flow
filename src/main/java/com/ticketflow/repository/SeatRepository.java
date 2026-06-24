package com.ticketflow.repository;

import com.ticketflow.domain.Seat;

public class SeatRepository extends InMemoryRepository<Seat, Long> {
    // inherits findById, findAll, save, deleteById
}