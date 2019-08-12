package com.airbnb.android.core.models;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.generated.GenCalendarDay;
import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.jitney.event.logging.DsNightAvailabilityStatus.p086v1.C1987DsNightAvailabilityStatus;
import com.bugsnag.android.Severity;
import com.google.common.collect.FluentIterable;
import java.util.Collection;

public class CalendarDay extends GenCalendarDay {
    public static final Creator<CalendarDay> CREATOR = new Creator<CalendarDay>() {
        public CalendarDay[] newArray(int size) {
            return new CalendarDay[size];
        }

        public CalendarDay createFromParcel(Parcel source) {
            CalendarDay object = new CalendarDay();
            object.readFromParcel(source);
            return object;
        }
    };
    private Boolean mModifiedAvailability;

    public enum AvailabilityType {
        Available("available"),
        Unavailable("unavailable"),
        UnavailablePersistent("unavailable_persistent"),
        UnavailableByBookingWindow("unavailable_by_booking_window");
        
        public final String updateRequestValue;

        private AvailabilityType(String updateRequestValue2) {
            this.updateRequestValue = updateRequestValue2;
        }

        public static AvailabilityType getTypeFromKey(String AvailabilityKey) {
            AvailabilityType result = (AvailabilityType) FluentIterable.from((E[]) values()).firstMatch(CalendarDay$AvailabilityType$$Lambda$1.lambdaFactory$(AvailabilityKey)).orNull();
            if (result == null) {
                BugsnagWrapper.notify((Throwable) new RuntimeException("Unexpected Availability: " + AvailabilityKey), Severity.WARNING);
            }
            return result;
        }

        public static AvailabilityType booleanToAvailabilityType(boolean available) {
            return available ? Available : Unavailable;
        }
    }

    public enum Type {
        Reserved,
        ExternalBusy,
        HostBusy,
        MinDaysNoticeBusy,
        MaxDaysNoticeBusy,
        TurnoverDaysBusy,
        Available,
        Blackout,
        ExpiredRequest,
        MaxNightsCap,
        NestedListing,
        Unknown
    }

    public CalendarDay() {
    }

    public CalendarDay(AirDate date, boolean available) {
        setDate(date);
        setAvailable(available);
    }

    public static boolean equals(CalendarDay day1, CalendarDay day2) {
        if (day1 == null) {
            return day2 == null;
        }
        return day1.equals(day2);
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalendarDay)) {
            return false;
        }
        CalendarDay that = (CalendarDay) o;
        if (this.mAvailable != that.mAvailable) {
            return false;
        }
        if (this.mDate != null) {
            if (!this.mDate.equals(that.mDate)) {
                return false;
            }
        } else if (that.mDate != null) {
            return false;
        }
        if (this.mExternalCalendar != null) {
            if (!this.mExternalCalendar.equals(that.mExternalCalendar)) {
                return false;
            }
        } else if (that.mExternalCalendar != null) {
            return false;
        }
        if (this.mPriceInfo != null) {
            if (!this.mPriceInfo.equals(that.mPriceInfo)) {
                return false;
            }
        } else if (that.mPriceInfo != null) {
            return false;
        }
        if (!reservationDaysEqual(this.mReservation, that.mReservation)) {
            return false;
        }
        if (this.mType != null) {
            if (!this.mType.equals(that.mType)) {
                return false;
            }
        } else if (that.mType != null) {
            return false;
        }
        if (this.mSubtype != null) {
            if (!this.mSubtype.equals(that.mSubtype)) {
                return false;
            }
        } else if (that.mSubtype != null) {
            return false;
        }
        if (this.mNotes != null) {
            if (!this.mNotes.equals(that.mNotes)) {
                return false;
            }
        } else if (that.mNotes != null) {
            return false;
        }
        if (this.mReason != null) {
            z = this.mReason.equals(that.mReason);
        } else if (that.mReason != null) {
            z = false;
        }
        return z;
    }

    private boolean reservationDaysEqual(Reservation r1, Reservation r2) {
        if (r1 == null) {
            if (r2 == null) {
                return true;
            }
            return false;
        } else if (r2 == null) {
            return false;
        } else {
            if (!r1.equals(r2) || !r1.getStatus().equals(r2.getStatus()) || !r1.getCheckinDate().equals(r2.getCheckinDate()) || !r1.getCheckoutDate().equals(r2.getCheckoutDate())) {
                return false;
            }
            return true;
        }
    }

    public Type getCalendarDayType() {
        if (isAvailable()) {
            return Type.Available;
        }
        if (getReservation() != null) {
            return (!getReservation().isExpired() || !"busy_by_expired_reservation".equals(getSubtype())) ? Type.Reserved : Type.ExpiredRequest;
        }
        if (getExternalCalendar() != null) {
            return Type.ExternalBusy;
        }
        if ("blackout".equals(getType())) {
            return Type.Blackout;
        }
        if (!ListUtils.isEmpty((Collection<?>) getNestedBusyDetails())) {
            return Type.NestedListing;
        }
        if ("rule".equals(getType())) {
            if ("min_days_notice".equals(getSubtype())) {
                return Type.MinDaysNoticeBusy;
            }
            if (CalendarRulesRequestConstants.MAX_DAYS_NOTICE.equals(getSubtype())) {
                if (isHostBusy()) {
                    return Type.HostBusy;
                }
                return Type.MaxDaysNoticeBusy;
            } else if ("turnover_days".equals(getSubtype())) {
                return Type.TurnoverDaysBusy;
            } else {
                if ("max_nights_cap".equals(getSubtype())) {
                    return Type.MaxNightsCap;
                }
            }
        } else if (!isAvailable()) {
            return Type.HostBusy;
        }
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown CalendarDay type:  type= " + getType() + ", subtype=" + getSubtype()));
        return Type.Unknown;
    }

    public boolean isBlockedForExceedingMaxDayCap() {
        return !isAvailable() && Type.MaxNightsCap == getCalendarDayType();
    }

    public boolean isBlockedForBlackout() {
        return !isAvailable() && Type.Blackout == getCalendarDayType();
    }

    public boolean toggleAvailability() {
        boolean z = true;
        if (this.mModifiedAvailability != null) {
            if (this.mModifiedAvailability.booleanValue()) {
                z = false;
            }
            this.mModifiedAvailability = Boolean.valueOf(z);
        } else {
            if (this.mAvailable) {
                z = false;
            }
            this.mModifiedAvailability = Boolean.valueOf(z);
        }
        return this.mModifiedAvailability.booleanValue();
    }

    public void setAvailability(boolean available) {
        this.mModifiedAvailability = Boolean.valueOf(available);
    }

    public boolean isAvailable() {
        if (this.mModifiedAvailability != null) {
            return this.mModifiedAvailability.booleanValue();
        }
        return this.mAvailable;
    }

    public boolean isModified() {
        return this.mModifiedAvailability != null;
    }

    public Long getReservationId() {
        if (this.mReservation == null || this.mReservation.getId() == 0) {
            return Long.valueOf(-1);
        }
        return Long.valueOf(this.mReservation.getId());
    }

    public boolean isReserved() {
        return (getReservation() == null || getReservation().getConfirmationCode() == null) ? false : true;
    }

    public ReservationStatus getStatus() {
        if (this.mReservation == null) {
            return ReservationStatus.Unknown;
        }
        return this.mReservation.getStatus();
    }

    /* access modifiers changed from: 0000 */
    public void resetAvailability() {
        this.mModifiedAvailability = null;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public boolean isUsingDemandBasedPricing() {
        return com.airbnb.android.core.models.CalendarDayPriceInfo.Type.DemandBased == getPriceInfo().getType();
    }

    public boolean hasExpired(AirDateTime expirationTime) {
        AirDateTime syncedAt = getServerSyncedAt();
        return syncedAt == null || syncedAt.isBefore(expirationTime);
    }

    public C1987DsNightAvailabilityStatus getDsNightAvailabilityStatus() {
        switch (getCalendarDayType()) {
            case ExpiredRequest:
            case Available:
                return C1987DsNightAvailabilityStatus.Available;
            case Reserved:
                return C1987DsNightAvailabilityStatus.Booked;
            default:
                return C1987DsNightAvailabilityStatus.Blocked;
        }
    }

    public String getNotes() {
        String notes = super.getNotes();
        if (notes == null) {
            return "";
        }
        return notes;
    }

    public String getFormattedNativePrice() {
        if (getPriceInfo() != null) {
            return getPriceInfo().getFormattedNativePrice();
        }
        return null;
    }

    public String getBusyReason(Resources r, CalendarRule calendarRule) {
        String blockingRule = getReason();
        if (!TextUtils.isEmpty(blockingRule)) {
            return blockingRule;
        }
        switch (getCalendarDayType()) {
            case ExpiredRequest:
                return r.getString(C0716R.string.calendar_availablity_expired_request);
            case ExternalBusy:
                return getExternalCalendarNotes(r);
            case MaxDaysNoticeBusy:
                if (calendarRule == null) {
                    return r.getString(C0716R.string.calendar_availablity_max_days_notice);
                }
                return r.getString(C0716R.string.calendar_availablity_based_on_x_month_booking_window, new Object[]{Integer.valueOf(MaxDaysNoticeSetting.daysToMonths(calendarRule.getMaxDaysNotice().getDays()))});
            case MaxNightsCap:
                return r.getString(C0716R.string.calendar_details_max_days_cap_reached);
            case MinDaysNoticeBusy:
                return r.getString(C0716R.string.calendar_availablity_min_days_notice);
            case NestedListing:
                if (FeatureToggles.showNestedListings() && !ListUtils.isEmpty((Collection<?>) getNestedBusyDetails())) {
                    return r.getString(C0716R.string.calendar_availablity_nested_listing_name, new Object[]{((NestedBusyDetail) getNestedBusyDetails().get(0)).getNestedListing().getName()});
                }
            case TurnoverDaysBusy:
                return r.getString(C0716R.string.calendar_availablity_turnover_days);
        }
        return null;
    }

    public String getExternalCalendarNotes(Resources r) {
        CalendarDayExternalCalendar externalCalendar = getExternalCalendar();
        if (externalCalendar == null || externalCalendar.getName() == null) {
            return null;
        }
        String externalNotes = externalCalendar.getNotes();
        if (externalNotes.length() > 0) {
            return r.getString(C0716R.string.calendar_external_sync_with_note, new Object[]{externalNotes, externalCalendar.getName()});
        }
        return r.getString(C0716R.string.calendar_external_sync, new Object[]{externalCalendar.getName()});
    }
}
