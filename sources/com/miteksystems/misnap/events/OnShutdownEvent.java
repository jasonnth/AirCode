package com.miteksystems.misnap.events;

public class OnShutdownEvent {
    public final int errorCode;
    public final String errorReason;

    public OnShutdownEvent(int resultCode, String errorReason2) {
        this.errorCode = resultCode;
        if (errorReason2 == null) {
            errorReason2 = "";
        }
        this.errorReason = errorReason2;
    }
}
