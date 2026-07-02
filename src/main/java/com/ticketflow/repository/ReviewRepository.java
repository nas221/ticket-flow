package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.Review;

@Repository
public class ReviewRepository extends InMemoryRepository<Review, Long> {
    // inherits findById, findAll, save, deleteById
}