package com.ticketflow.repository;

import com.ticketflow.domain.Payment;

public class PaymentRepository extends InMemoryRepository<Payment, Long> {
    // inherits findById, findAll, save, deleteById
}