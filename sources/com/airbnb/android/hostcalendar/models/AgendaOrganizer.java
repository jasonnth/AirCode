package com.airbnb.android.hostcalendar.models;

import android.support.p000v4.util.LongSparseArray;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Reservation;
import java.util.Collection;
import java.util.HashMap;

public class AgendaOrganizer {
    private final HashMap<AirDate, LongSparseArray<ListingDayAgenda>> ListingDayAgendasByDateByListingId = new HashMap<>();
    private AirDate maxCheckinDate = null;
    private AirDate maxDate = null;
    private AirDate minDate = null;

    public void update(Collection<Reservation> reservations, AirDate today) {
        for (Reservation reservation : reservations) {
            long listingId = reservation.getListing().getId();
            String listingName = reservation.getListing().getName();
            getOrCreateListingDayAgenda(listingId, listingName, reservation.getCheckinDate()).setCheckinReservation(reservation);
            if (this.minDate == null || this.minDate.isAfter(reservation.getCheckinDate())) {
                this.minDate = reservation.getCheckinDate();
            }
            if (this.maxCheckinDate == null || reservation.getCheckinDate().isAfter(this.maxCheckinDate)) {
                this.maxCheckinDate = reservation.getCheckinDate();
            }
            getOrCreateListingDayAgenda(listingId, listingName, reservation.getCheckoutDate()).setCheckoutReservation(reservation);
            if (this.maxDate == null || this.maxDate.isBefore(reservation.getCheckoutDate())) {
                this.maxDate = reservation.getCheckoutDate();
            }
            if (isOngoingReservation(reservation, today)) {
                getOrCreateListingDayAgenda(listingId, listingName, today).setOngoingReservation(reservation);
            }
        }
    }

    private boolean isOngoingReservation(Reservation reservation, AirDate today) {
        return today.isBetweenInclusive(reservation.getCheckinDate().plusDays(1), reservation.getCheckoutDate().plusDays(-1));
    }

    private ListingDayAgenda getOrCreateListingDayAgenda(long listingId, String listingName, AirDate date) {
        LongSparseArray<ListingDayAgenda> reservationBlocksByListingId = (LongSparseArray) this.ListingDayAgendasByDateByListingId.get(date);
        if (reservationBlocksByListingId == null) {
            reservationBlocksByListingId = new LongSparseArray<>();
            this.ListingDayAgendasByDateByListingId.put(date, reservationBlocksByListingId);
        }
        ListingDayAgenda reservationBlock = (ListingDayAgenda) reservationBlocksByListingId.get(listingId);
        if (reservationBlock != null) {
            return reservationBlock;
        }
        ListingDayAgenda reservationBlock2 = new ListingDayAgenda(listingId, listingName, date);
        reservationBlocksByListingId.put(listingId, reservationBlock2);
        return reservationBlock2;
    }

    public void clear() {
        this.ListingDayAgendasByDateByListingId.clear();
        this.minDate = null;
        this.maxCheckinDate = null;
        this.maxDate = null;
    }

    public boolean isEmpty() {
        return this.ListingDayAgendasByDateByListingId.isEmpty();
    }

    public AirDate getMinDate() {
        return this.minDate;
    }

    public AirDate getMaxCheckinDate() {
        return this.maxCheckinDate;
    }

    public AirDate getMaxDate() {
        return this.maxDate;
    }

    public LongSparseArray<ListingDayAgenda> get(AirDate date) {
        return (LongSparseArray) this.ListingDayAgendasByDateByListingId.get(date);
    }
}
