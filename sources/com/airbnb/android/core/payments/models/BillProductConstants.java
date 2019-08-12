package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.models.ParcelableBigDecimal;

public final class BillProductConstants {
    public static final String GIFT_CARD_SERVER_KEY = "GiftCredit";
    public static final String HOMES_SERVER_KEY = "Reservation2";
    public static final ParcelableBigDecimal ONE_MILLION = new ParcelableBigDecimal(ParcelableBigDecimal.valueOf(1000000));
    public static final String PAID_AMENITY_SERVER_KEY = "PaidAmenityOrder";
    public static final String RESY_RESERVATION_SERVER_KEY = "ResyReservation";
    public static final String TRIP_SERVER_KEY = "MtTrip";
}
