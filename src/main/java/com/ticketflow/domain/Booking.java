package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

import java.time.LocalDateTime;

public class Booking implements Identifiable<Long> {
    private final Long id;
    private final Long userId;
    private final Long eventId;
    private final Long seatId;
    private String status;
    private final LocalDateTime bookedAt;


    public Booking(Long id, Long userId, Long eventId, Long seatId, String status ){
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.seatId = seatId;
        this.status = status;
        this.bookedAt = LocalDateTime.now();
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
    public String getStatus(){
        return status;
    }
    public void setStatus(String confirmed){
        this.status = status;
    }
}

