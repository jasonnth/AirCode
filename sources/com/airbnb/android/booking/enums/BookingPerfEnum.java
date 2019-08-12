package com.airbnb.android.booking.enums;

import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.businesstravel.IntentPredictionConstants;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;

public enum BookingPerfEnum {
    DATES(CancellationAnalytics.VALUE_PAGE_DATES),
    GUESTS(FindTweenAnalytics.GUESTS),
    BUSINESS_TRAVEL(IntentPredictionConstants.BusinessTravelServerKey),
    P4_RESERVATION("p4_reservation"),
    P4_RESERVATION_ERROR("p4_reservation_error");
    
    private String value;

    private BookingPerfEnum(String value2) {
        this.value = value2;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return getValue();
    }
}
