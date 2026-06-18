package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Review implements Identifiable<Long> {
    long id;
    String review_message;
    @Override
    public Long getId() {
        return null;
    }
}
