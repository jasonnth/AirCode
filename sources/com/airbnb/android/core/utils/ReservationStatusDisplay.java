package com.airbnb.android.core.utils;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;

public class ReservationStatusDisplay {
    private static final int COLOR_ACTION_REQUIRED = C0716R.color.n2_arches;
    private static final int COLOR_ACTIVE = C0716R.color.n2_babu;
    private static final int COLOR_INACTIVE = C0716R.color.n2_hof;
    private static final ReservationStatusDisplay UNKNOWN = new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_unknown);
    public final int colorRes;
    public final int stringRes;

    private ReservationStatusDisplay(int colorRes2, int stringRes2) {
        this.colorRes = colorRes2;
        this.stringRes = stringRes2;
    }

    public String getString(Context context) {
        return context.getString(this.stringRes);
    }

    public int getColor(Context context) {
        return ContextCompat.getColor(context, this.colorRes);
    }

    public static ReservationStatusDisplay forGuest(Reservation reservation) {
        return forGuest(reservation.getStatus(), reservation.getEndDate(), hasExpired(reservation.getPendingExpiresAt()), reservation.hasPendingAlterations());
    }

    public static ReservationStatusDisplay forGuest(TripInformationProvider provider) {
        return forGuest(provider.getStatus(), provider.getEndDate(), provider.hasExpired(), provider.hasPendingAlterations());
    }

    public static ReservationStatusDisplay forGuest(Thread thread) {
        if (thread.hasDates()) {
            return forGuest(thread.getReservationStatus(), thread.getEndDate(), hasExpired(thread.getExpiresAt()), thread.hasPendingAlterationRequest());
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Cannot create reservation status without dates"));
        return UNKNOWN;
    }

    private static ReservationStatusDisplay forGuest(ReservationStatus status, AirDate endDate, boolean hasExpired, boolean hasPendingAlterations) {
        if (hasPendingAlterations) {
            return new ReservationStatusDisplay(COLOR_ACTION_REQUIRED, C0716R.string.status_pending_alteration);
        }
        switch (status) {
            case Preapproved:
                return new ReservationStatusDisplay(colorForExpirableAction(hasExpired), C0716R.string.status_preapproved);
            case New:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_unknown);
            case Accepted:
                return new ReservationStatusDisplay(AirDate.isInPast(endDate) ? COLOR_INACTIVE : COLOR_ACTIVE, C0716R.string.status_accepted);
            case Inquiry:
                return new ReservationStatusDisplay(colorForExpirableAction(hasExpired), C0716R.string.status_inquiry);
            case Pending:
                return new ReservationStatusDisplay(colorForExpirableAction(hasExpired), C0716R.string.status_pending);
            case Denied:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_denied);
            case NotPossible:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_not_possible);
            case Cancelled:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_cancelled);
            case Timedout:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_timeout);
            case SpecialOffer:
                return new ReservationStatusDisplay(colorForExpirableAction(hasExpired), C0716R.string.special_offer);
            case Checkpoint:
                return new ReservationStatusDisplay(COLOR_ACTION_REQUIRED, C0716R.string.status_checkpoint_guest);
            case WaitingForPayment:
                return new ReservationStatusDisplay(COLOR_ACTION_REQUIRED, C0716R.string.status_waiting_for_payment);
            case Message:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_message);
            case Unknown:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_unknown);
            default:
                throw new IllegalArgumentException(status.toString());
        }
    }

    @Deprecated
    public static ReservationStatusDisplay forStatus(ReservationStatus status) {
        switch (status) {
            case Preapproved:
                return new ReservationStatusDisplay(C0716R.color.c_ebisu, C0716R.string.status_preapproved);
            case New:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.reservation_status_unknown);
            case Accepted:
                return new ReservationStatusDisplay(C0716R.color.n2_text_color_muted, C0716R.string.status_accepted);
            case Inquiry:
                return new ReservationStatusDisplay(C0716R.color.c_ebisu, C0716R.string.status_inquiry);
            case Pending:
                return new ReservationStatusDisplay(C0716R.color.c_ebisu, C0716R.string.status_pending);
            case Denied:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.status_denied);
            case NotPossible:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.status_not_possible);
            case Cancelled:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.status_cancelled);
            case Timedout:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.status_timeout);
            case SpecialOffer:
                return new ReservationStatusDisplay(C0716R.color.c_ebisu, C0716R.string.special_offer);
            case Checkpoint:
                return new ReservationStatusDisplay(C0716R.color.c_ebisu, C0716R.string.status_checkpoint_guest);
            case WaitingForPayment:
                return new ReservationStatusDisplay(C0716R.color.c_beach_light, C0716R.string.status_waiting_for_payment);
            case Message:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.reservation_status_message);
            case Unknown:
                return new ReservationStatusDisplay(C0716R.color.c_foggy, C0716R.string.reservation_status_unknown);
            default:
                throw new IllegalArgumentException(status.toString());
        }
    }

    public static ReservationStatusDisplay forHost(TripInformationProvider provider) {
        return forHost(provider.getStatus(), provider.getStartDate(), provider.getEndDate(), provider.hasPendingAlterations());
    }

    public static ReservationStatusDisplay forHost(Reservation reservation) {
        return forHost(reservation.getReservationStatus(), reservation.getStartDate(), reservation.getEndDate(), reservation.hasPendingAlterations());
    }

    public static ReservationStatusDisplay forHost(Thread thread) {
        return forHost(thread.getReservationStatus(), thread.getStartDate(), thread.getEndDate(), thread.hasPendingAlterationRequest());
    }

    private static ReservationStatusDisplay forHost(ReservationStatus status, AirDate startDate, AirDate endDate, boolean hasPendingAlterations) {
        if (hasPendingAlterations) {
            return new ReservationStatusDisplay(COLOR_ACTION_REQUIRED, C0716R.string.status_pending_alteration);
        }
        switch (status) {
            case Preapproved:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_preapproved);
            case New:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_unknown);
            case Accepted:
                return forHostAccepted(startDate, endDate);
            case Inquiry:
                return new ReservationStatusDisplay(COLOR_ACTION_REQUIRED, C0716R.string.status_inquiry);
            case Pending:
                return new ReservationStatusDisplay(COLOR_ACTION_REQUIRED, C0716R.string.status_request);
            case Denied:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_denied);
            case NotPossible:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_not_possible);
            case Cancelled:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_cancelled);
            case Timedout:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_timeout);
            case SpecialOffer:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.special_offer);
            case Checkpoint:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_checkpoint_guest);
            case WaitingForPayment:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_waiting_for_payment);
            case Message:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_message);
            case Unknown:
                return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.reservation_status_unknown);
            default:
                throw new IllegalArgumentException(status.toString());
        }
    }

    private static ReservationStatusDisplay forHostAccepted(AirDate startDate, AirDate endDate) {
        if (startDate == null || endDate == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Cannot create reservation status without dates"));
            return UNKNOWN;
        }
        AirDate today = AirDate.today();
        if (today.isAfter(endDate)) {
            return new ReservationStatusDisplay(COLOR_INACTIVE, C0716R.string.status_past_guest);
        }
        if (startDate.isWithinOneWeekInFuture()) {
            return new ReservationStatusDisplay(COLOR_ACTIVE, C0716R.string.status_upcoming);
        }
        if (today.isBetweenInclusive(startDate, endDate)) {
            return new ReservationStatusDisplay(COLOR_ACTIVE, C0716R.string.status_current);
        }
        return new ReservationStatusDisplay(COLOR_ACTIVE, C0716R.string.status_accepted);
    }

    private static boolean hasExpired(AirDateTime expirationTime) {
        return expirationTime != null && AirDateTime.now().isAfter(expirationTime);
    }

    private static int colorForExpirableAction(boolean hasExpired) {
        return hasExpired ? COLOR_INACTIVE : COLOR_ACTION_REQUIRED;
    }
}
