package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Seat implements Identifiable<Long> {
    private final Long id;
    private final Long venueId;
    private final String row;
    private final int number;
    private final String category;

    public Seat(Long id, Long venueId, String row, int number, String category){
        this.id = id;
        this.venueId = venueId;
        this.row = row;
        this.number = number;
        this.category = category;
    }
    @Override
    public Long getId() {
        return id;
    }

    public Long getVenueId() {
        return venueId;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public String getCategory() {
        return category;
    }
    @Override
    public String toString() {
        return "Seat{id=" + id + ", venueId=" + venueId + ", row='" + row +
               "', number=" + number + ", category='" + category + "'}";
    }
}

