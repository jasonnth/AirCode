package com.airbnb.android.core.events;

public class VerifiedIDStatusEvent {
    private boolean mSuccess;

    public VerifiedIDStatusEvent(boolean success) {
        this.mSuccess = success;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public void setSuccess(boolean success) {
        this.mSuccess = success;
    }
}
