package com.airbnb.android.core.analytics;

import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.Navigation.p162v1.ImpressionEvent.Builder;

public class ReservationCancellationLogger extends BaseLogger {
    public ReservationCancellationLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public static NavigationTag getNavigationTag(ReservationCancellationReason reason, boolean hasNote) {
        if (reason == null) {
            return NavigationTag.HostReservationCancelFlowHostNotAvailable;
        }
        switch (reason) {
            case Unavailable:
                return NavigationTag.HostReservationCancelFlowHostNotAvailable;
            case PriceOrTripLength:
                return NavigationTag.HostReservationCancelFlowHostDifferentPrice;
            case GuestCancellation:
                return NavigationTag.HostReservationCancelFlowHostGuestCancel;
            case Emergency:
                return NavigationTag.HostReservationCancelFlowHostExtenuatingCircumstance;
            case IbPenaltyReachLimit:
                return NavigationTag.HostReservationCancelFlowHostNoPenaltyMax3;
            case AntiDiscrimination:
                return NavigationTag.HostReservationCancelFlowHostAntiDiscrimination;
            case Concerned:
                return NavigationTag.HostReservationCancelFlowHostUncomfortable;
            case IbPenaltyFree:
            case CustomPenalty:
                return NavigationTag.HostReservationCancelFlowHostNoPenalty;
            case PenaltyFreeTrial:
                return NavigationTag.HostReservationCancelFlowHostPenaltyFreeTrial;
            case ReviewPenalties:
                return NavigationTag.HostReservationCancelFlowHostReviewPenalties;
            case MissedEarnings:
                return NavigationTag.HostReservationCancelFlowHostMissedEarnings;
            case GuestEmpathy:
                return NavigationTag.HostReservationCancelFlowHostGuestEmpathy;
            case FollowUp:
                return NavigationTag.HostReservationCancelFlowHostFollowupHelp;
            case Other:
                return hasNote ? NavigationTag.HostReservationCancelFlowHostShareConcernsNote : NavigationTag.HostReservationCancelFlowHostOther;
            case ConfirmationNote:
                return hasNote ? NavigationTag.HostReservationCancelFlowHostPersonalNote : NavigationTag.HostReservationCancelFlowHostConfirmCancel;
            case Canceled:
                return NavigationTag.HostReservationCancelFlowHostCancelComplete;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid ReservationCancellationReason for getting page in ReasonPickerFragment" + reason));
                return null;
        }
    }

    public void onReasonSelected(Reservation reservation, ReservationCancellationReason reason) {
        publish(new Builder(context(), getNavigationTag(reason, false).trackingName, "reservation").info(Strap.make().mo11639kv("reservation_id", Long.toString(reservation.getId())).mo11639kv("listing_id", Long.toString(reservation.getListing().getId()))));
    }

    public void onReasonSelectedWithNote(Reservation reservation, ReservationCancellationReason reason, String note) {
        boolean hasNote = !TextUtils.isEmpty(note);
        Builder builder = new Builder(context(), getNavigationTag(reason, hasNote).trackingName, "reservation");
        Strap kv = Strap.make().mo11639kv("reservation_id", Long.toString(reservation.getId())).mo11639kv("listing_id", Long.toString(reservation.getListing().getId()));
        String str = "note";
        if (!hasNote) {
            note = "";
        }
        publish(builder.info(kv.mo11639kv(str, note)));
    }
}
