package com.airbnb.android.lib.paidamenities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaidAmenityStatus {
    UNLISTED(0),
    LISTED(1);
    
    private final int statusCode;

    @JsonCreator
    private PaidAmenityStatus(int statusCode2) {
        this.statusCode = statusCode2;
    }

    @JsonValue
    public int getStatusCode() {
        return this.statusCode;
    }
}
