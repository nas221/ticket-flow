package com.ticketflow.service;

import com.ticketflow.domain.Booking;

public class NotificationService {

    public void sendBookingConfirmation(Booking booking) {
        // Stand-in for a real email/SMTP call, which is why it's simulated with latency
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println("Confirmation email sent for booking " + booking.getId()
                + " (user " + booking.getUserId() + ")");
    }
}