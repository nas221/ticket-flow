package com.ticketflow.service;
import com.ticketflow.domain.*;
import com.ticketflow.repository.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.*;

public class AnalyticsService {




    /**
     * Provides read-only analytics over TicketFlow data.
     * All methods are pure stream pipelines — no side effects.
     */


        private final BookingRepository bookings;
        private final EventRepository events;
        private final SeatRepository seats;

        // Constructor injection: we receive the repositories we need.
        // We don't create them ourselves — whoever creates AnalyticsService hands them in.
        // This is the Dependency Injection pattern we'll formalise in Section 6.
        public AnalyticsService(BookingRepository bookings,
                                EventRepository events,
                                SeatRepository seats) {
            this.bookings = bookings;
            this.events = events;
            this.seats = seats;
        }

        /**
         * Total revenue per event: sum the price for each CONFIRMED booking.
         *
         * Returns Map<Long, BigDecimal> where key = eventId, value = total revenue.
         *
         * Pipeline:
         * 1. Start with all bookings
         * 2. Keep only CONFIRMED ones (cancelled bookings don't generate revenue)
         * 3. Group them by eventId
         * 4. For each group, sum the event price × 1 (one seat per booking)
         */
        public Map<Long, BigDecimal> revenuePerEvent() {
            // Build a quick lookup: eventId → event (to get the price)
            Map<Long, Event> eventById = events.findAll().stream()
                    .collect(Collectors.toMap(Event::getId, e -> e));

            return bookings.findAll().stream()
                    .filter(b -> "CONFIRMED".equals(b.getStatus()))
                    .collect(Collectors.groupingBy(
                            Booking::getEventId,                       // group by event
                            Collectors.reducing(
                                    BigDecimal.ZERO,                        // start at £0
                                    b -> eventById.getOrDefault(           // price of this booking's event
                                                    b.getEventId(), new Event(0L,"",0L,null,BigDecimal.ZERO))
                                            .getPriceGbp(),
                                    BigDecimal::add                         // accumulate
                            )
                    ));
        }

        /**
         * Occupancy rate per event: how full was each event?
         * occupancy = (confirmed bookings / venue capacity) * 100
         *
         * Returns Map<Long, Double> where key = eventId, value = % occupancy (0–100)
         */
        public Map<Long, Double> occupancyRatePerEvent() {
            // Count confirmed bookings per event
            Map<Long, Long> confirmedPerEvent = bookings.findAll().stream()
                    .filter(b -> "CONFIRMED".equals(b.getStatus()))
                    .collect(Collectors.groupingBy(Booking::getEventId, Collectors.counting()));

            // Count seats per event (how many seats does the venue for this event have?)
            Map<Long, Long> seatsPerEvent = seats.findAll().stream()
                    .collect(Collectors.groupingBy(Seat::getVenueId, Collectors.counting()));

            return events.findAll().stream()
                    .collect(Collectors.toMap(
                            Event::getId,
                            e -> {
                                long booked = confirmedPerEvent.getOrDefault(e.getId(), 0L);
                                long capacity = seatsPerEvent.getOrDefault(e.getVenueId(), 1L);
                                return (booked * 100.0) / capacity;
                            }
                    ));
        }

        /**
         * Top N events by revenue.
         * Uses sorted() with reversed comparator, then limit().
         */
        public List<Event> topEventsByRevenue(int n) {
            Map<Long, BigDecimal> revenue = revenuePerEvent();
            return events.findAll().stream()
                    .sorted(Comparator.comparing(
                            e -> revenue.getOrDefault(e.getId(), BigDecimal.ZERO),
                            Comparator.reverseOrder()   // highest revenue first
                    ))
                    .limit(n)
                    .collect(Collectors.toList());
        }

        /**
         * Average rating per event (from reviews).
         * Uses Collectors.averagingInt.
         */
        public Map<Long, Double> averageRatingPerEvent(List<Review> reviews) {
            return reviews.stream()
                    .collect(Collectors.groupingBy(
                            Review::getEventId,
                            Collectors.averagingInt(Review::getRating)
                    ));
        }
    }

