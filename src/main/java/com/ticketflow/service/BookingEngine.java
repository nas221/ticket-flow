package com.ticketflow.service;

import com.ticketflow.domain.*;
import com.ticketflow.repository.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class BookingEngine {

    private final BookingRepository bookings;
    private final EventRepository events;

    private final Map<String, Long> seatLocks = new ConcurrentHashMap<>();

    // AtomicLong: a thread-safe counter for generating booking IDs
    private final AtomicLong idSequence = new AtomicLong(1);

    public BookingEngine(BookingRepository bookings, EventRepository events) {
        this.bookings = bookings;
        this.events = events;
    }

    /**
     * Attempt to book a seat. Returns the Booking if successful,
     * or empty Optional if the seat was already taken.

     */
    public Optional<Booking> book(long userId, long eventId, long seatId) {
        String key = eventId + ":" + seatId;  // unique key for this seat at this event
        long bookingId = idSequence.getAndIncrement();

        // putIfAbsent returns null if the key was not already present
        Long existing = seatLocks.putIfAbsent(key, bookingId);

        if (existing != null) {
            return Optional.empty();  // seat already taken
        }


        Booking booking = new Booking(bookingId, userId, eventId, seatId, "PENDING");
        booking.setStatus("CONFIRMED");
        bookings.save(booking);
        return Optional.of(booking);
    }

    public void cancel(long bookingId) {
        bookings.findByID(bookingId).ifPresent(b -> {
            b.setStatus("CANCELLED");
            bookings.save(b);
            // Release the seat lock so it can be rebooked
            seatLocks.remove(b.getEventId() + ":" + b.getSeatId());
        });
    }
}