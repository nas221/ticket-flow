package com.ticketflow.domain;

import com.ticketflow.repository.Identifiable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment implements Identifiable<Long> {
    private final Long id;
    private final Long bookingId;
    private final BigDecimal amountGDP;
    private String status;
    private LocalDateTime paidAt;

    public Payment(Long id, Long bookingId, BigDecimal amountGDP, String status){
        this.id = id;
        this.bookingId = bookingId;
        this.amountGDP = amountGDP;
        this.status = "PENDING";
    }




    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", bookingId=" + bookingId + ", amountGBP=£" + amountGDP +
               ", status='" + status + "', paidAt=" + paidAt + "}";
    }
}
