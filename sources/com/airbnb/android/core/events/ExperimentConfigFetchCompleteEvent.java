package com.airbnb.android.core.events;

public class ExperimentConfigFetchCompleteEvent {
    public final boolean success;
    public final long userId;

    public ExperimentConfigFetchCompleteEvent(boolean success2, long userId2) {
        this.success = success2;
        this.userId = userId2;
    }
}
