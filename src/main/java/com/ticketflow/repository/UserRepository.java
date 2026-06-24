package com.ticketflow.repository;

import com.ticketflow.domain.User;

public class UserRepository extends InMemoryRepository<User, Long> {
    // inherits findById, findAll, save, deleteById
}