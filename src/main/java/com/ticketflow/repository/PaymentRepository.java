package com.ticketflow.repository;

import org.springframework.stereotype.Repository;

import com.ticketflow.domain.Payment;

@Repository
public class PaymentRepository extends InMemoryRepository<Payment, Long> {
    // inherits findById, findAll, save, deleteById
}