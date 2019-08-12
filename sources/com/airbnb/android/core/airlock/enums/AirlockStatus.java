package com.airbnb.android.core.airlock.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AirlockStatus {
    INITIALIZED(0),
    IN_PROGRESS(1),
    SATISFIED(2),
    UNSATISFIED(3);
    
    private final int statusCode;

    private AirlockStatus(int statusCode2) {
        this.statusCode = statusCode2;
    }

    @JsonValue
    public int getStatusCode() {
        return this.statusCode;
    }
}
