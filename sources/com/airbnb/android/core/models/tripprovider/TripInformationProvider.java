package com.airbnb.android.core.models.tripprovider;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Inquiry;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.CurrencyFormatter;

public abstract class TripInformationProvider implements Parcelable {
    public abstract CalendarDays getCalendarDays();

    public abstract String getCancellationPolicy();

    public abstract String getCancellationPolicyKey();

    public abstract AirDate getEndDate();

    public abstract Post getFirstPost();

    public abstract int getGuestCount();

    public abstract GuestDetails getGuestDetails();

    public abstract User getGuestIfPossible();

    public abstract String getGuestPhotoUrl();

    public abstract String getGuestTotalPriceFormatted(CurrencyFormatter currencyFormatter);

    public abstract Price getHostPrice();

    public abstract int getHostSubtotalAmount();

    public abstract String getHostSubtotalCurrency();

    public abstract String getHostSubtotalFormatted();

    public abstract String getHostTotalPriceFormatted(CurrencyFormatter currencyFormatter);

    public abstract Listing getListing();

    public abstract User getPrimaryHost();

    public abstract Reservation getReservation();

    public abstract int getReservedNightsCount();

    public abstract SpecialOffer getSpecialOffer();

    public abstract AirDate getStartDate();

    public abstract ReservationStatus getStatus();

    public abstract long getThreadId();

    public abstract boolean hasExpired();

    public abstract boolean hasPendingAlterations();

    public abstract boolean hasReservation();

    public abstract boolean isKoreanStrictBooking();

    public abstract boolean requiresResponse();

    public abstract void setCalendarDays(CalendarDays calendarDays);

    public static TripInformationProvider create(Reservation reservation) {
        return new ReservationInformationProvider(reservation);
    }

    public static TripInformationProvider create(Inquiry inquiry) {
        return new InquiryInformationProvider(inquiry);
    }
}
