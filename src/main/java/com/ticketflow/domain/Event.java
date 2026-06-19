package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * A concert, show, or sporting event.
 * priceGbp: ticket price in British pounds.
 * BigDecimal (not double) for money — doubles have floating-point
 * rounding errors, which is catastrophic for financial calculations.
 */
public class Event implements Identifiable<Long> {
    private final Long id;
    private String title;
    private final Long venueId;
    private LocalDateTime dateTime;
    private BigDecimal priceGbp;

    public Event(Long id, String title, Long venueId,
                 LocalDateTime dateTime, BigDecimal priceGbp) {
        this.id = id;
        this.title = title;
        this.venueId = venueId;
        this.dateTime = dateTime;
        this.priceGbp = priceGbp;
    }

    @Override public Long getId() { return id; }
    public String getTitle() { return title; }
    public Long getVenueId() { return venueId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public BigDecimal getPriceGbp() { return priceGbp; }
    public void setTitle(String title) { this.title = title; }
    public void setPriceGbp(BigDecimal priceGbp) { this.priceGbp = priceGbp; }

    @Override
    public String toString() {
        return "Event{id=" + id + ", title='" + title + "', venueId=" + venueId +
               ", dateTime=" + dateTime + ", priceGbp=£" + priceGbp + "}";
    }
}