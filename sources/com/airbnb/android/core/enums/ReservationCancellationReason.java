package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.models.ReservationCancellationReasonInfo;
import java.util.Arrays;
import java.util.List;

public enum ReservationCancellationReason {
    Unavailable("1"),
    PriceOrTripLength("7"),
    Concerned("6"),
    GuestCancellation("4"),
    Other("99"),
    AnotherGuest("200"),
    CannotHost("201"),
    Emergency("3"),
    DifferentTripLength("202"),
    DifferentPrice("203"),
    ChangeReservation("207"),
    AntiDiscrimination(null),
    GuestBadReview("204"),
    GuestBrokeHouseRules("205"),
    OtherGuestConcerns("206"),
    ReviewPenalties(null),
    PenaltyFreeTrial(null),
    IbPenaltyFree(null),
    IbPenaltyReachLimit(null),
    CustomPenalty(null),
    MissedEarnings(null),
    GuestEmpathy(null),
    ConfirmationNote(null),
    Canceled(null),
    GoodGuest(null),
    ClearExpecation(null),
    FollowUp(null),
    UndergoingMaintenance("2");
    
    private final String serverKey;

    private ReservationCancellationReason(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public String getReasonTitleStr(ReservationCancellationInfo reservationCancellationInfo) {
        if (reservationCancellationInfo == null) {
            return null;
        }
        ReservationCancellationReasonInfo reasonInfo = reservationCancellationInfo.findReason(this);
        if (reasonInfo != null) {
            return reasonInfo.getTitle();
        }
        return null;
    }

    public int getReasonSubTitleStrId() {
        switch (this) {
            case GoodGuest:
                return C0716R.string.reservation_cancellation_instant_book_settings;
            case ClearExpecation:
                return C0716R.string.reservation_cancellation_house_rules;
            case ChangeReservation:
                return C0716R.string.different_trip_price_change_reservation_subtitle;
            default:
                return 0;
        }
    }

    public static List<ReservationCancellationReason> getMainReasons(boolean showConcerned) {
        if (showConcerned) {
            return Arrays.asList(new ReservationCancellationReason[]{Unavailable, PriceOrTripLength, Concerned, GuestCancellation, Other});
        }
        return Arrays.asList(new ReservationCancellationReason[]{Unavailable, PriceOrTripLength, GuestCancellation, Other});
    }

    public static List<ReservationCancellationReason> getSubReasons(ReservationCancellationReason reason) {
        switch (reason) {
            case Unavailable:
                return Arrays.asList(new ReservationCancellationReason[]{AnotherGuest, CannotHost, Emergency});
            case PriceOrTripLength:
                return Arrays.asList(new ReservationCancellationReason[]{DifferentTripLength, DifferentPrice, ChangeReservation});
            case Concerned:
                return Arrays.asList(new ReservationCancellationReason[]{GuestBadReview, GuestBrokeHouseRules, OtherGuestConcerns});
            default:
                return Arrays.asList(new ReservationCancellationReason[0]);
        }
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public static boolean isTopLevelReason(ReservationCancellationReason reason) {
        if (reason == Unavailable || reason == PriceOrTripLength || reason == Concerned || reason == GuestCancellation || reason == Other) {
            return true;
        }
        return false;
    }
}
