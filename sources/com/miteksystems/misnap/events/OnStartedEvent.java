package com.miteksystems.misnap.events;

public class OnStartedEvent {
    public final int captureMode;
    public final int errorCode;

    public OnStartedEvent(int captureMode2, int errorCode2) {
        this.captureMode = captureMode2;
        this.errorCode = errorCode2;
    }
}
