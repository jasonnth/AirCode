package com.miteksystems.misnap.events;

public class StartEvent {
    public final String jobSettings;

    public StartEvent(String jobSettings2) {
        this.jobSettings = jobSettings2;
    }
}
