package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.User;

@Repository
public class UserRepository extends InMemoryRepository<User, Long> {
    // inherits findById, findAll, save, deleteById
}