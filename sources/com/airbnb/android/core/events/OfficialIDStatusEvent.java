package com.airbnb.android.core.events;

public class OfficialIDStatusEvent {
    private boolean mSuccess;

    public OfficialIDStatusEvent(boolean success) {
        this.mSuccess = success;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public void setSuccess(boolean success) {
        this.mSuccess = success;
    }
}
