package com.airbnb.android.lib.businesstravel.models;

import com.airbnb.android.lib.businesstravel.IntentPredictionConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.FluentIterable;

public enum IntentPredictionType {
    BusinessTravel(IntentPredictionConstants.BusinessTravelServerKey),
    VacationRental(IntentPredictionConstants.VacationRentalServerKey),
    Unknown("");
    
    private final String serverKey;

    private IntentPredictionType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    @JsonCreator
    public static IntentPredictionType getIntentPredictionTypeFromServerKey(String serverKey2) {
        return (IntentPredictionType) FluentIterable.from((E[]) values()).firstMatch(IntentPredictionType$$Lambda$1.lambdaFactory$(serverKey2)).mo41059or(Unknown);
    }
}
