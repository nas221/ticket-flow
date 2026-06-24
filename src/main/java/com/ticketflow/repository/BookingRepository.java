package com.ticketflow.repository;

import com.ticketflow.domain.Booking;

public class BookingRepository extends InMemoryRepository<Booking, Long> {
    // inherits findById, findAll, save, deleteById
}