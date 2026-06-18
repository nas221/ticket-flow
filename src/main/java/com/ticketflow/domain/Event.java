package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Event implements Identifiable<Long> {
    long id;
    String eventName;
    @Override
    public Long getId(){
        return id;
    }
}
enum EVENTSTATUS{
    UPCOMING,
    ONGOING,
    COMPLETED
}
