package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Payment implements Identifiable<Long> {
    long id;

    public Payment(long id) {
        this.id = id;
    }

    @Override
    public Long getId(){
        return id;
    }
}
