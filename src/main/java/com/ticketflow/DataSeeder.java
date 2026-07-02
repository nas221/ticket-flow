package com.ticketflow;

import com.ticketflow.domain.*;
import com.ticketflow.repository.*;
import com.ticketflow.service.BookingEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository users;
    private final VenueRepository venues;
    private final EventRepository events;
    private final SeatRepository seats;
    private final BookingEngine bookingEngine;

    public DataSeeder(UserRepository users, VenueRepository venues, EventRepository events,
                       SeatRepository seats, BookingEngine bookingEngine) {
        this.users = users;
        this.venues = venues;
        this.events = events;
        this.seats = seats;
        this.bookingEngine = bookingEngine;
    }

    @Override
    public void run(String... args) {
        users.save(new User(1L, "Alice", "hash1", "alice@example.com"));
        users.save(new User(2L, "Bob", "hash2", "bob@example.com"));

        venues.save(new Venue(1L, "The O2 Arena", "London", 20000));

        events.save(new Event(1L, "Coldplay Live", 1L,
                LocalDateTime.of(2026, 8, 15, 20, 0), new BigDecimal("75.00")));

        for (int i = 1; i <= 50; i++) {
            seats.save(new Seat((long) i, 1L, "A", i, "STANDARD"));
        }

        bookingEngine.book(1L, 1L, 1L);
    }
}