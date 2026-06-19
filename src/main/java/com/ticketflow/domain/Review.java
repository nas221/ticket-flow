package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Review implements Identifiable<Long> {
    private final Long id;
    private final Long userId;
    private final Long eventId;
    private final int rating;
    private String comment;

    public Review(Long id, Long userId, Long eventId, int rating, String comment){
        if(rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.rating = rating;
        this.comment = comment;
    }



    @Override
    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getUserId() {
        return userId;
    }
}
