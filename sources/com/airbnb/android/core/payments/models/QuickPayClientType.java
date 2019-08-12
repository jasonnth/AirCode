package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.constants.ActivityConstants;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuickPayClientType {
    Unknown(""),
    Trip(ActivityConstants.ACTIVITY_EXPERIENCES_QUICK_PAY),
    PaidAmenity(ActivityConstants.ACTIVITY_PAID_AMENITY_QUICK_PAY),
    GiftCard(ActivityConstants.ACTIVITY_GIFT_CARDS_QUICK_PAY),
    ResyReservation(ActivityConstants.ACTIVITY_RESY_RESERVATION_QUICK_PAY),
    Homes(ActivityConstants.ACTIVITY_HOMES_QUICK_PAY);
    
    private final String clientType;

    private QuickPayClientType(String clientType2) {
        this.clientType = clientType2;
    }

    @JsonValue
    public String getClientType() {
        return this.clientType;
    }
}
