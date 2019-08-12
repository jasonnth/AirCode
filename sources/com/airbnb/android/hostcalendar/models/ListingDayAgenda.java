package com.airbnb.android.hostcalendar.models;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Reservation;

public class ListingDayAgenda {
    private Reservation checkinReservation = null;
    private Reservation checkoutReservation = null;
    private final AirDate date;
    private final long listingId;
    private final String listingName;
    private Reservation ongoingReservation = null;

    public ListingDayAgenda(long listingId2, String listingName2, AirDate date2) {
        this.listingId = listingId2;
        this.listingName = listingName2;
        this.date = date2;
    }

    public String getId() {
        return String.valueOf(this.listingId) + ":" + this.date.getIsoDateString();
    }

    public long getListingId() {
        return this.listingId;
    }

    public String getListingName() {
        return this.listingName;
    }

    public AirDate getDate() {
        return this.date;
    }

    public Reservation getCheckoutReservation() {
        return this.checkoutReservation;
    }

    public void setCheckoutReservation(Reservation checkoutReservation2) {
        this.checkoutReservation = checkoutReservation2;
    }

    public Reservation getCheckinReservation() {
        return this.checkinReservation;
    }

    public void setCheckinReservation(Reservation checkinReservation2) {
        this.checkinReservation = checkinReservation2;
    }

    public Reservation getOngoingReservation() {
        return this.ongoingReservation;
    }

    public void setOngoingReservation(Reservation ongoingReservation2) {
        this.ongoingReservation = ongoingReservation2;
    }
}
