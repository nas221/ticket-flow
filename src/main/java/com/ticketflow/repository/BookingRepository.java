package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.Booking;

@Repository
public class BookingRepository extends InMemoryRepository<Booking, Long> {
    // inherits findById, findAll, save, deleteById
}