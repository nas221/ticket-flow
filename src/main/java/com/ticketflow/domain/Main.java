package com.ticketflow.domain;

import com.ticketflow.service.*;
import com.ticketflow.repository.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;

public class Main {
    public Main() throws ExecutionException, InterruptedException {
    }

    static void main(String[] args) {

        // Create repositories
        UserRepository users = new UserRepository();
        EventRepository events = new EventRepository();

        // Save some data
        User alice = users.save(new User(1L, "alice@example.com", "hash1", "Alice"));
        User bob   = users.save(new User(2L, "bob@example.com",   "hash2", "Bob"));

        Event concert = events.save(new Event(
                1L, "Coldplay Live", 1L,
                LocalDateTime.of(2026, 8, 15, 20, 0),
                new BigDecimal("75.00")
        ));

        // Retrieve and print
        System.out.println("All users: " + users.findAll());
        users.findByID(1L).ifPresent(u ->
                System.out.println("Found: " + u)
        );
        System.out.println("Event: " + concert);
        //Generate seed data for testing of analytics

        BookingRepository bookingRepo = new BookingRepository();
        SeatRepository seatRepo = new SeatRepository();

        //Generating seed data for the seating repo
        for(int i = 1; i <= 50; i++){
            seatRepo.save(new Seat( (long)i, 2L, "A", i, "STANDARD"));
        }

        Booking b1 = bookingRepo.save(new Booking(1L, 1L, 1L, 1L, "UNBOOKED"));
        b1.setStatus("CONFIRMED"); bookingRepo.save(b1);

        EventRepository eventRepo = new EventRepository();

        AnalyticsService analytics = new AnalyticsService(bookingRepo, eventRepo, seatRepo);
        System.out.println("Revenue per event: " + String.valueOf(analytics.revenuePerEvent()));
        System.out.println("Occupancy: " + analytics.occupancyRatePerEvent());
        System.out.println("Top 3 events: " + analytics.topEventsByRevenue(3));
    }




}