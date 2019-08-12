package com.airbnb.android.core.models.tripprovider;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
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
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.CurrencyFormatter;

class InquiryInformationProvider extends TripInformationProvider {
    public static final Creator<InquiryInformationProvider> CREATOR = new Creator<InquiryInformationProvider>() {
        public InquiryInformationProvider[] newArray(int size) {
            return new InquiryInformationProvider[size];
        }

        public InquiryInformationProvider createFromParcel(Parcel source) {
            return new InquiryInformationProvider((Inquiry) source.readParcelable(Listing.class.getClassLoader()));
        }
    };
    private CalendarDays calendarDays;
    private final Inquiry inquiry;

    public InquiryInformationProvider(Inquiry inquiry2) {
        this.inquiry = (Inquiry) Check.notNull(inquiry2);
    }

    public AirDate getStartDate() {
        return this.inquiry.getStartDate();
    }

    public AirDate getEndDate() {
        return this.inquiry.getEndDate();
    }

    public boolean hasPendingAlterations() {
        return false;
    }

    public String getHostTotalPriceFormatted(CurrencyFormatter currencyHelper) {
        return getHostPrice().getTotal().formattedForDisplay();
    }

    public String getGuestTotalPriceFormatted(CurrencyFormatter currencyHelper) {
        double priceNative;
        if (this.inquiry.getSpecialOffer() == null) {
            priceNative = (double) this.inquiry.getInquiryPriceNative();
        } else {
            priceNative = (double) this.inquiry.getSpecialOffer().getPriceNative();
        }
        return currencyHelper.formatNativeCurrency(priceNative, true);
    }

    public ReservationStatus getStatus() {
        return this.inquiry.getReservationStatus();
    }

    public String getCancellationPolicy() {
        return getListing().getCancellationPolicy();
    }

    public String getCancellationPolicyKey() {
        return getListing().getCancellationPolicyKey();
    }

    public GuestDetails getGuestDetails() {
        GuestDetails guestDetails;
        if (this.inquiry.getInquiryPost() != null) {
            guestDetails = this.inquiry.getInquiryPost().getGuestDetails();
        } else {
            guestDetails = null;
        }
        if (guestDetails == null || !guestDetails.isValid() || guestDetails.totalGuestCount() != getGuestCount()) {
            return null;
        }
        return guestDetails;
    }

    public int getGuestCount() {
        return this.inquiry.getNumberOfGuests();
    }

    public int getReservedNightsCount() {
        return this.inquiry.getNights();
    }

    public boolean hasReservation() {
        return false;
    }

    public Listing getListing() {
        return this.inquiry.getListing();
    }

    public Reservation getReservation() {
        throw new UnsupportedOperationException("Not a reservation");
    }

    public SpecialOffer getSpecialOffer() {
        return this.inquiry.getSpecialOffer();
    }

    public User getPrimaryHost() {
        return getListing().getPrimaryHost();
    }

    public User getGuestIfPossible() {
        return this.inquiry.getGuest();
    }

    public long getThreadId() {
        return this.inquiry.getThreadId();
    }

    public Price getHostPrice() {
        return this.inquiry.getPricingQuote().getHostPayoutBreakdown();
    }

    public boolean requiresResponse() {
        return this.inquiry.isRequiresResponse();
    }

    public Post getFirstPost() {
        return this.inquiry.getInquiryPost();
    }

    public String getGuestPhotoUrl() {
        return this.inquiry.getGuest().getThumbnailUrl();
    }

    public String getHostSubtotalFormatted() {
        return this.inquiry.getPricingQuote().getHostSubtotalAmount().formattedForDisplay();
    }

    public int getHostSubtotalAmount() {
        return this.inquiry.getPricingQuote().getHostSubtotalAmount().getAmount().intValue();
    }

    public String getHostSubtotalCurrency() {
        return this.inquiry.getPricingQuote().getHostSubtotalAmount().getCurrency();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.inquiry, flags);
    }

    public CalendarDays getCalendarDays() {
        return this.calendarDays;
    }

    public void setCalendarDays(CalendarDays calendarDays2) {
        this.calendarDays = calendarDays2;
    }

    public boolean hasExpired() {
        AirDateTime expiration = this.inquiry.getExpiresAt();
        return expiration != null && AirDateTime.now().isAfter(expiration);
    }

    public boolean isKoreanStrictBooking() {
        return this.inquiry.getInquiryPost().isKoreanStrictBooking();
    }
}
