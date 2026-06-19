package com.ticketflow.domain;

import com.ticketflow.domain.*;
import com.ticketflow.repository.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

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
    }
}