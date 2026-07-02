


package com.ticketflow.util;

import com.ticketflow.repository.BookingRepository;
import com.ticketflow.repository.EventRepository;
import com.ticketflow.service.BookingEngine;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingLoadTest {

    private static final int THREAD_COUNT = 100;
    private static final long EVENT_ID = 1L;
    private static final long SEAT_ID = 42L;

    public static void main(String[] args) throws InterruptedException {
        runConcurrentBookingTest();
    }

    public static void runConcurrentBookingTest() throws InterruptedException {
        BookingEngine engine = new BookingEngine(new BookingRepository(), new EventRepository());

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        // startLatch holds all threads until they are all ready, maximising contention
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(THREAD_COUNT);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final long userId = i + 1;
            executor.submit(() -> {
                try {
                    startLatch.await();
                    boolean booked = engine.book(userId, EVENT_ID, SEAT_ID).isPresent();
                    if (booked) successCount.incrementAndGet();
                    else        failCount.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        startLatch.countDown(); // release all 100 threads at once
        doneLatch.await();
        executor.shutdown();

        System.out.printf("Threads: %d | Succeeded: %d | Failed: %d%n",
                THREAD_COUNT, successCount.get(), failCount.get());

        if (successCount.get() != 1) {
            throw new AssertionError(
                "FAIL: expected exactly 1 successful booking but got " + successCount.get());
        }
        if (failCount.get() != THREAD_COUNT - 1) {
            throw new AssertionError(
                "FAIL: expected " + (THREAD_COUNT - 1) + " rejections but got " + failCount.get());
        }

        System.out.println("PASS: exactly one booking succeeded for seat " + SEAT_ID);
    }
}