package com.airbnb.android.core.events;

public class ErfExperimentsUpdatedEvent {
    private final String updatedExperiment;

    public ErfExperimentsUpdatedEvent() {
        this(null);
    }

    public ErfExperimentsUpdatedEvent(String experimentName) {
        this.updatedExperiment = experimentName;
    }

    public boolean isAllExperimentsUpdated() {
        return this.updatedExperiment == null;
    }
}
