package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

public class Venue implements Identifiable<Long> {
    private final Long id;
    String name;
    private String city;
    private int totalCapacity;

    public Venue(Long id, String name, String city, int totalCapacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.totalCapacity = totalCapacity;
    }
    public String getName()
    {
        return name;
    }
    public String getCity(){
        return city;
    }
    public void setCity(){

    }



    @Override
    public Long getId() {
        return id;
    }
}
