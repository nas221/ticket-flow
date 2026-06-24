package com.ticketflow.service;

import com.ticketflow.domain.Event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

public class EventSearchFilter {
    private String titleContains;
    private Long venueId;
    private LocalDateTime startsAtOrAfter;
    private LocalDateTime startsAtOrBefore;
    private BigDecimal minPriceGbp;
    private BigDecimal maxPriceGbp;

    public EventSearchFilter() {
    }

    public EventSearchFilter(String titleContains,
                             Long venueId,
                             LocalDateTime startsAtOrAfter,
                             LocalDateTime startsAtOrBefore,
                             BigDecimal minPriceGbp,
                             BigDecimal maxPriceGbp) {
        this.titleContains = titleContains;
        this.venueId = venueId;
        this.startsAtOrAfter = startsAtOrAfter;
        this.startsAtOrBefore = startsAtOrBefore;
        this.minPriceGbp = minPriceGbp;
        this.maxPriceGbp = maxPriceGbp;
    }

    public boolean matches(Event event) {
        if (event == null) {
            return false;
        }

        if (titleContains != null && !titleContains.isBlank()) {
            String expected = titleContains.toLowerCase(Locale.ROOT);
            String actual = event.getTitle() == null ? "" : event.getTitle().toLowerCase(Locale.ROOT);
            if (!actual.contains(expected)) {
                return false;
            }
        }

        if (venueId != null && !venueId.equals(event.getVenueId())) {
            return false;
        }

        if (startsAtOrAfter != null) {
            LocalDateTime dateTime = event.getDateTime();
            if (dateTime == null || dateTime.isBefore(startsAtOrAfter)) {
                return false;
            }
        }

        if (startsAtOrBefore != null) {
            LocalDateTime dateTime = event.getDateTime();
            if (dateTime == null || dateTime.isAfter(startsAtOrBefore)) {
                return false;
            }
        }

        if (minPriceGbp != null) {
            BigDecimal price = event.getPriceGbp();
            if (price == null || price.compareTo(minPriceGbp) < 0) {
                return false;
            }
        }

        if (maxPriceGbp != null) {
            BigDecimal price = event.getPriceGbp();
            if (price == null || price.compareTo(maxPriceGbp) > 0) {
                return false;
            }
        }

        return true;
    }

    public String getTitleContains() {
        return titleContains;
    }

    public void setTitleContains(String titleContains) {
        this.titleContains = titleContains;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public LocalDateTime getStartsAtOrAfter() {
        return startsAtOrAfter;
    }

    public void setStartsAtOrAfter(LocalDateTime startsAtOrAfter) {
        this.startsAtOrAfter = startsAtOrAfter;
    }

    public LocalDateTime getStartsAtOrBefore() {
        return startsAtOrBefore;
    }

    public void setStartsAtOrBefore(LocalDateTime startsAtOrBefore) {
        this.startsAtOrBefore = startsAtOrBefore;
    }

    public BigDecimal getMinPriceGbp() {
        return minPriceGbp;
    }

    public void setMinPriceGbp(BigDecimal minPriceGbp) {
        this.minPriceGbp = minPriceGbp;
    }

    public BigDecimal getMaxPriceGbp() {
        return maxPriceGbp;
    }

    public void setMaxPriceGbp(BigDecimal maxPriceGbp) {
        this.maxPriceGbp = maxPriceGbp;
    }
}
