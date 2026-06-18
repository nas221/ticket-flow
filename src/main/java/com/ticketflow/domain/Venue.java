package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Venue implements Identifiable<Long> {
    long id;
    String venueName;

    @Override
    public Long getId() {
        return null;
    }
}
