package com.airbnb.android.core.events;

public class ResetPasswordEntryEvent {
    private final String secret;

    public ResetPasswordEntryEvent(String secret2) {
        this.secret = secret2;
    }

    public String getSecret() {
        return this.secret;
    }
}
