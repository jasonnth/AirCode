package com.airbnb.android.core.models;

import com.google.common.collect.FluentIterable;

public enum RoomAvailabilityType {
    InstantBook("Instant Book"),
    BusinessTravelReady("Business Travel Ready");
    
    public final String serverKey;

    private RoomAvailabilityType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static RoomAvailabilityType fromKey(String key) {
        return (RoomAvailabilityType) FluentIterable.from((E[]) values()).firstMatch(RoomAvailabilityType$$Lambda$1.lambdaFactory$(key)).orNull();
    }
}
