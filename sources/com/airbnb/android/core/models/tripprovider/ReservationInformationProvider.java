package com.airbnb.android.core.models.tripprovider;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.CurrencyFormatter;

class ReservationInformationProvider extends TripInformationProvider {
    public static final Creator<ReservationInformationProvider> CREATOR = new Creator<ReservationInformationProvider>() {
        public ReservationInformationProvider[] newArray(int size) {
            return new ReservationInformationProvider[size];
        }

        public ReservationInformationProvider createFromParcel(Parcel source) {
            return new ReservationInformationProvider((Reservation) source.readParcelable(Reservation.class.getClassLoader()));
        }
    };
    private CalendarDays calendarDays;
    private final Reservation reservation;

    ReservationInformationProvider(Reservation reservation2) {
        this.reservation = reservation2;
    }

    public AirDate getStartDate() {
        return this.reservation.getStartDate();
    }

    public AirDate getEndDate() {
        return this.reservation.getEndDate();
    }

    public boolean hasPendingAlterations() {
        return this.reservation.hasPendingAlterations();
    }

    public String getGuestTotalPriceFormatted(CurrencyFormatter currencyHelper) {
        return this.reservation.getPricingQuote().getTotalPrice().formattedForDisplay();
    }

    public String getHostTotalPriceFormatted(CurrencyFormatter currencyHelper) {
        return getHostPrice().getTotal().formattedForDisplay();
    }

    public ReservationStatus getStatus() {
        return this.reservation.getStatus();
    }

    public String getCancellationPolicy() {
        return this.reservation.getCancellationPolicy();
    }

    public String getCancellationPolicyKey() {
        return this.reservation.getCancellationPolicyKey();
    }

    public GuestDetails getGuestDetails() {
        GuestDetails details = this.reservation.getGuestDetails();
        if (details == null || !details.isValid() || details.totalGuestCount() != getGuestCount()) {
            return null;
        }
        return details;
    }

    public int getGuestCount() {
        return this.reservation.getGuestCount();
    }

    public int getReservedNightsCount() {
        return this.reservation.getReservedNightsCount();
    }

    public boolean hasReservation() {
        return true;
    }

    public Listing getListing() {
        return this.reservation.getListing();
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public SpecialOffer getSpecialOffer() {
        throw new UnsupportedOperationException("Not an inquiry");
    }

    public User getPrimaryHost() {
        return this.reservation.getPrimaryHost();
    }

    public User getGuestIfPossible() {
        return this.reservation.getGuest();
    }

    public long getThreadId() {
        return (long) this.reservation.getThreadId();
    }

    public Price getHostPrice() {
        return this.reservation.getPricingQuote().getHostPayoutBreakdown();
    }

    public boolean requiresResponse() {
        return false;
    }

    public Post getFirstPost() {
        return null;
    }

    public String getGuestPhotoUrl() {
        return this.reservation.getGuest().getThumbnailUrl();
    }

    public String getHostSubtotalFormatted() {
        return this.reservation.getPricingQuote().getHostSubtotalAmount().formattedForDisplay();
    }

    public int getHostSubtotalAmount() {
        return this.reservation.getPricingQuote().getHostSubtotalAmount().getAmount().intValue();
    }

    public String getHostSubtotalCurrency() {
        return this.reservation.getPricingQuote().getHostSubtotalAmount().getCurrency();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.reservation, flags);
    }

    public CalendarDays getCalendarDays() {
        return this.calendarDays;
    }

    public void setCalendarDays(CalendarDays calendarDays2) {
        this.calendarDays = calendarDays2;
    }

    public boolean hasExpired() {
        return false;
    }

    public boolean isKoreanStrictBooking() {
        return this.reservation.isKoreanStrictBooking();
    }
}
