package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Booking implements Identifiable<Long> {
    private final Long id;
    private final Long userId;
    private final Long eventId;
    private final Long seatId;

    public Booking(Long id, Long userId, Long eventId, Long seatId ){
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.seatId = seatId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getSeatId() {
        return seatId;
    }
}

