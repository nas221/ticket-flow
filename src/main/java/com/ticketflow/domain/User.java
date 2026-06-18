package com.ticketflow.domain;
import com.ticketflow.repository.Identifiable;

public class User implements Identifiable<Long> {
    private long id;
    private String displayName;
    private String passwordHash;
    private String email;

    // Constructor to initialize the User object with all fields
    public User(long id, String displayName, String passwordHash, String email) {
        this.id = id;
        this.displayName = displayName;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    @Override
    public Long getId() {
        return id;
    }
    public String getDisplayName() {
        return displayName;
    }
   public String getEmail(){
        return email;
   }
}
