package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateInterval;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.interfaces.Reservationable;
import com.airbnb.android.core.models.Price.Type;
import com.airbnb.android.core.models.generated.GenReservation;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Collection;

public class Reservation extends GenReservation implements Reservationable {
    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }

        public Reservation createFromParcel(Parcel source) {
            Reservation object = new Reservation();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final int DEFAULT_CHECKIN_HOUR = 15;
    private static final int DEFAULT_CHECKOUT_HOUR = 11;
    public static final int INVALID_ID = -1;
    public static final int LONG_TERM_RESERVATION_MINIMUM_NIGHTS = 28;

    public Reservation() {
    }

    public Reservation(int reservedNightsCount, int guestCount, int inquiryPriceNative, String startDate, User host, User guest, Listing listing) {
        this.mBasePrice = -1;
        this.mId = -1;
        this.mReservedNightsCount = reservedNightsCount;
        this.mGuestCount = guestCount;
        setStartDate(new AirDate(startDate));
        this.mTotalPrice = inquiryPriceNative;
        this.mPayoutPriceNative = inquiryPriceNative;
        this.mReservationStatus = ReservationStatus.Inquiry;
        this.mHost = host;
        this.mGuest = guest;
        this.mListing = listing;
        this.mIsArtificial = true;
    }

    public Reservation(int id, AirDate startDate) {
        this.mId = (long) id;
        this.mStartDate = startDate;
        this.mReservedNightsCount = 1;
    }

    /* Debug info: failed to restart local var, previous not found, register: 4 */
    public Reservation getReservation() {
        return getId() > 0 ? this : this.mReservation;
    }

    public boolean hasTaxes() {
        return this.mPricingQuote != null && this.mPricingQuote.hasLineItemType(Type.Taxes);
    }

    public Review getHostReview() {
        return patchReviewInfo(super.getHostReview(), getHost(), getGuest(), ReviewRole.Host);
    }

    public Review getGuestReview() {
        return patchReviewInfo(super.getGuestReview(), getGuest(), getHost(), ReviewRole.Guest);
    }

    private Review patchReviewInfo(Review review, User author, User recipient, ReviewRole role) {
        if (review == null) {
            return null;
        }
        review.setAuthorId(author.getId());
        review.setAuthor(author);
        review.setRecipient(recipient);
        review.setReviewRole(role.apiString);
        return review;
    }

    @WrappedObject("listing")
    @JsonProperty("listing")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setListing(Listing listing) {
        this.mListing = listing;
    }

    @WrappedObject("user")
    @JsonProperty("host")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setHost(User user) {
        this.mHost = user;
    }

    @WrappedObject("user")
    @JsonProperty("guest")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setGuest(User user) {
        this.mGuest = user;
    }

    @WrappedObject("host_payout_breakdown")
    @JsonProperty("host_price_breakdown")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setHostPayoutBreakdown(Price value) {
        this.mHostPayoutBreakdown = value;
    }

    @JsonProperty("nights")
    public void setReservedNightsCount(int value) {
        if (getCheckOut() != null) {
            this.mReservedNightsCount = getCheckIn().getDaysUntil(getCheckOut());
        } else if (value < 0) {
            throw new IllegalStateException("Negative number of nights for reservationId=" + getId());
        } else {
            this.mReservedNightsCount = value;
        }
    }

    public int getStayDuration() {
        Check.notNull(getCheckIn());
        Check.notNull(getCheckOut());
        return getCheckIn().getDaysUntil(getCheckOut());
    }

    @JsonProperty("status")
    public void setStatus(String statusString) {
        this.mReservationStatus = ReservationStatus.findStatus(statusString);
    }

    public ReservationStatus getStatus() {
        return calculateCorrectedReservationState(this.mReservationStatus, isGuestPendingIdentityVerification());
    }

    public boolean isPending() {
        return ReservationStatus.Pending == getStatus();
    }

    public boolean isAccepted() {
        return ReservationStatus.Accepted == getStatus();
    }

    public boolean isAwaitingPayment() {
        return ReservationStatus.WaitingForPayment == getStatus();
    }

    public boolean isDenied() {
        return ReservationStatus.Denied == getStatus();
    }

    public boolean isCancelled() {
        return ReservationStatus.Cancelled == getStatus();
    }

    public boolean isCheckpointed() {
        return ReservationStatus.Checkpoint == getStatus();
    }

    public void setCheckpointedStatus() {
        setStatus(ReservationStatus.Checkpoint.key);
    }

    public boolean isLongTerm() {
        return getReservedNightsCount() >= 28;
    }

    public User getHost() {
        if (super.getHost() != null) {
            return super.getHost();
        }
        return getPrimaryHost();
    }

    public boolean isUserHost(User user) {
        return (user != null && user.equals(getHost())) || (getListing() != null && getListing().canUserHost(user));
    }

    public boolean isUserGuest(User currentUser) {
        return currentUser != null && currentUser.equals(getGuest());
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) obj;
        if (this.mId > 0 && other.mId > 0) {
            if (this.mId != other.mId) {
                z = false;
            }
            return z;
        } else if (!TextUtils.isEmpty(this.mConfirmationCode) && !TextUtils.isEmpty(other.mConfirmationCode)) {
            return this.mConfirmationCode.equals(other.mConfirmationCode);
        } else {
            BugsnagWrapper.notify(new Throwable("cannot evaluate Reservation.equals(), no ID or confirmation code"));
            return false;
        }
    }

    public AirDate getStartDate() {
        if (getCheckIn() != null) {
            return getCheckIn();
        }
        return super.getStartDate();
    }

    public AirDate getEndDate() {
        if (getCheckOut() == null) {
            Check.state(getReservedNightsCount() > 0, "Number of nights must be a positive number");
            setCheckOut(getStartDate().plusDays(getReservedNightsCount()));
        }
        return getCheckOut();
    }

    public AirDate getCheckinDate() {
        return getStartDate();
    }

    public AirDate getCheckoutDate() {
        return getEndDate();
    }

    public int getReservedNightsCount() {
        if (this.mReservedNightsCount > 0) {
            return this.mReservedNightsCount;
        }
        return getStartDate().getDaysUntil(getEndDate());
    }

    @Deprecated
    public int getTotalPrice() {
        if (getPricingQuote() == null || !getPricingQuote().hasTotalPrice()) {
            return super.getTotalPrice();
        }
        return getPricingQuote().getTotalPrice().getAmount().intValue();
    }

    public AirDateTime getCheckinTime() {
        Integer checkinHour = getListing().getCheckInTime();
        return new AirDateTime(getStartDate().getTimeInMillisAtStartOfDay()).plusHours(checkinHour != null ? checkinHour.intValue() : 15);
    }

    public AirDateTime getCheckoutTime() {
        Integer checkoutHour = getListing().getCheckOutTime();
        return new AirDateTime(getEndDate().getTimeInMillisAtStartOfDay()).plusHours(checkoutHour != null ? checkoutHour.intValue() : 11);
    }

    public boolean isUpcoming() {
        return getStartDate().isAfter(AirDate.today());
    }

    public boolean isCurrent() {
        return dateWithinReservation(this, AirDate.today());
    }

    public boolean isCheckInTomorrow() {
        return AirDate.today().plusDays(1).equals(getCheckinDate());
    }

    public boolean isCheckInToday() {
        return AirDate.today().equals(getCheckinDate());
    }

    public boolean isCheckOutToday() {
        return AirDate.today().equals(getCheckoutDate());
    }

    public boolean hasEnded() {
        return AirDate.today().isAfter(getEndDate());
    }

    private static boolean dateWithinReservation(Reservationable reservation, AirDate date) {
        return dateWithinReservation(reservation, date, true);
    }

    public static boolean dateWithinReservation(Reservationable reservation, AirDate date, boolean endInclusive) {
        return new AirDateInterval(reservation.getCheckinDate(), reservation.getCheckoutDate()).contains(date, true, endInclusive);
    }

    public boolean isAlterable() {
        return isAlterationViewable() && !isSharedItinerary();
    }

    public boolean isAlterationViewable() {
        return isAccepted() && !hasEnded() && !AppLaunchUtils.isIndiaRegion();
    }

    public boolean hasPendingAlterations() {
        return getFirstPendingAlteration() != null;
    }

    public boolean isPendingHostReview() {
        if (getStatus() != ReservationStatus.Accepted || !hasEnded()) {
            return false;
        }
        if (getHostReview() != null) {
            return getHostReview().isPending();
        }
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Reservation status is accepted and has ended but no review: " + getId()));
        return false;
    }

    public ReservationAlteration getFirstPendingAlteration() {
        if (this.mAlterations == null) {
            return null;
        }
        for (ReservationAlteration alteration : this.mAlterations) {
            if (alteration.isPending()) {
                return alteration;
            }
        }
        return null;
    }

    public String getHostBasePriceFormatted() {
        return super.getHostBasePriceFormatted();
    }

    public boolean isCancelableByUser(User user) {
        if (isSharedItinerary()) {
            return false;
        }
        if (isAccepted() && !hasEnded()) {
            return true;
        }
        if (isPending() || isAwaitingPayment()) {
            return isUserGuest(user);
        }
        return false;
    }

    public static ReservationStatus calculateCorrectedReservationState(ReservationStatus originalStatus, boolean isGuestPendingIdentityVerification) {
        if (originalStatus != ReservationStatus.Checkpoint || !isGuestPendingIdentityVerification) {
            return originalStatus;
        }
        return ReservationStatus.Pending;
    }

    public boolean hasArrivalTime() {
        return (getArrivalDetails() == null || getArrivalDetails().getGuestCheckinTimeFrom() == null || getArrivalDetails().getGuestCheckinTimeTo() == null) ? false : true;
    }

    public boolean isExpired() {
        return getStatus() == ReservationStatus.Timedout;
    }

    public boolean dateRangeOverlapsInclusive(AirDate startDateRange, AirDate endDateRange) {
        return getCheckinDate().isBetweenInclusive(startDateRange, endDateRange) || getCheckoutDate().isBetweenInclusive(startDateRange, endDateRange);
    }

    public boolean hasIncentive(String incentiveName) {
        if (ListUtils.isEmpty((Collection<?>) this.mIncentives) || !Trebuchet.launch(TrebuchetKeys.IB_TRIAL_INCENTIVES)) {
            return false;
        }
        for (Incentive i : this.mIncentives) {
            if (i.getName().equals(incentiveName)) {
                return true;
            }
        }
        return false;
    }

    public Incentive getIncentive(String incentiveName) {
        if (ListUtils.isEmpty((Collection<?>) this.mIncentives) || !Trebuchet.launch(TrebuchetKeys.IB_TRIAL_INCENTIVES)) {
            return null;
        }
        for (Incentive i : this.mIncentives) {
            if (i.getName().equals(incentiveName)) {
                return i;
            }
        }
        return null;
    }
}
