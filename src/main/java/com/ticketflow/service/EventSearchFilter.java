package com.ticketflow.service;

import com.ticketflow.domain.Event;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventSearchFilter {

    // Start with a predicate that accepts everything.
    // We'll .and() more conditions onto it as the caller adds filters.
    private Predicate<Event> predicate = e -> true;

    public EventSearchFilter withMaxPrice(BigDecimal max) {
        predicate = predicate.and(e -> e.getPriceGbp().compareTo(max) <= 0);
        return this;
    }

    public EventSearchFilter withTitleContaining(String keyword) {
        predicate = predicate.and(e ->
                e.getTitle().toLowerCase().contains(keyword.toLowerCase())
        );
        return this;
    }


    public EventSearchFilter withAfter(LocalDateTime after) {
        predicate = predicate.and(e -> e.getDateTime().isAfter(after));
        return this;
    }

    /**
     * Run the combined filter over a list of events.
     * @param allEvents the full list to search through
     * @return only events matching ALL added conditions
     */
    public List<Event> apply(List<Event> allEvents) {
        return allEvents.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}