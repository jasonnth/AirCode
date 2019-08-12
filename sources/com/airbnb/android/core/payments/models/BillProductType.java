package com.airbnb.android.core.payments.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BillProductType {
    Unknown(""),
    Trip(BillProductConstants.TRIP_SERVER_KEY),
    PaidAmenity(BillProductConstants.PAID_AMENITY_SERVER_KEY),
    GiftCredit(BillProductConstants.GIFT_CARD_SERVER_KEY),
    ResyReservation(BillProductConstants.RESY_RESERVATION_SERVER_KEY),
    Homes(BillProductConstants.HOMES_SERVER_KEY);
    
    private final String serverKey;

    private BillProductType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    public static BillProductType getProductTypeFromClientType(QuickPayClientType clientType) {
        switch (clientType) {
            case Trip:
                return Trip;
            case PaidAmenity:
                return PaidAmenity;
            case GiftCard:
                return GiftCredit;
            case ResyReservation:
                return ResyReservation;
            case Homes:
                return Homes;
            default:
                return Unknown;
        }
    }
}
