package com.airbnb.android.core.events;

public class P3ImpressionDurationEvent {
    private final long durationMs;
    private final long listingId;

    public P3ImpressionDurationEvent(long listingId2, long durationMs2) {
        this.listingId = listingId2;
        this.durationMs = durationMs2;
    }

    public long getListingId() {
        return this.listingId;
    }

    public long getDurationMs() {
        return this.durationMs;
    }
}
