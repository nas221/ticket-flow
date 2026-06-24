package com.ticketflow.repository;

import com.ticketflow.domain.Review;

public class ReviewRepository extends InMemoryRepository<Review, Long> {
    // inherits findById, findAll, save, deleteById
}