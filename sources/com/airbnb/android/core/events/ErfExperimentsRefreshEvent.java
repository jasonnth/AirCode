package com.airbnb.android.core.events;

public class ErfExperimentsRefreshEvent {
    private final boolean refreshSuccessful;

    public ErfExperimentsRefreshEvent(boolean refreshSuccessful2) {
        this.refreshSuccessful = refreshSuccessful2;
    }

    public boolean isRefreshSuccessful() {
        return this.refreshSuccessful;
    }
}
