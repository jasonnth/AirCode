package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;
import java.util.Arrays;
import java.util.List;

public enum DeactivateIBReason {
    GuestControl(C0716R.string.deactivate_ib_reason_guest_control, C0716R.string.deactivate_ib_reason_guest_control_title, 1),
    CalendarUpdate(C0716R.string.deactivate_ib_reason_calendar, C0716R.string.deactivate_ib_reason_calendar_title, 2),
    BetterOffer(C0716R.string.deactivate_ib_reason_better_offer, C0716R.string.deactivate_ib_better_offer_title, 3),
    UnwareIB(C0716R.string.deactivate_ib_reason_unware_ib, C0716R.string.deactivate_ib_unwared_ib_title, 4),
    Unlisted(C0716R.string.deactivate_ib_reason_unlisted, C0716R.string.deactivate_ib_reason_unlisted_title, 5),
    AirbnbRequirements(C0716R.string.deactivate_ib_guest_control_airbnb_requirements, C0716R.string.deactivate_ib_guest_control_airbnb_requirements_title, 6),
    HouseRules(C0716R.string.deactivate_ib_guest_control_house_rules, 0, 7),
    PreparationTime(C0716R.string.deactivate_ib_calendar_prepare_time, 0, 8),
    DistantRequest(C0716R.string.deactivate_ib_calendar_distant_request, 0, 9),
    TripLength(C0716R.string.deactivate_ib_better_offer_trip_length, 0, 10),
    UnawareHouseRules(C0716R.string.deactivate_ib_unware_ib_house_rules, 0, 11),
    UnawareCalendarUpdated(C0716R.string.deactivate_ib_unware_ib_calendar_updated, 0, 12);
    
    private final int loggingConstant;
    private final int reasonStrId;
    private final int reasonStrTitleId;

    private DeactivateIBReason(int reasonStrId2, int reasonStrTitleId2, int loggingConstant2) {
        this.reasonStrId = reasonStrId2;
        this.reasonStrTitleId = reasonStrTitleId2;
        this.loggingConstant = loggingConstant2;
    }

    public int getReasonStrId() {
        return this.reasonStrId;
    }

    public int getReasonStrTitleId() {
        return this.reasonStrTitleId;
    }

    public int getReasonStrSubtitleId() {
        switch (this) {
            case AirbnbRequirements:
                return C0716R.string.deactivate_ib_guest_control_airbnb_requirements_subtitle;
            case HouseRules:
                return C0716R.string.deactivate_ib_guest_control_house_rules_subtitle;
            case TripLength:
                return C0716R.string.deactivate_ib_better_offer_trip_length_subtitle;
            case UnawareHouseRules:
                return C0716R.string.deactivate_ib_unware_ib_house_rules_subtitle;
            case UnawareCalendarUpdated:
                return C0716R.string.deactivate_ib_unware_ib_calendar_updated_subtitle;
            default:
                return 0;
        }
    }

    public int getLoggingConstant() {
        return this.loggingConstant;
    }

    public static List<DeactivateIBReason> getMainReasons() {
        return Arrays.asList(new DeactivateIBReason[]{GuestControl, CalendarUpdate, BetterOffer, UnwareIB, Unlisted});
    }

    public static List<DeactivateIBReason> getSubReasons(DeactivateIBReason reason) {
        switch (reason) {
            case GuestControl:
                return Arrays.asList(new DeactivateIBReason[]{AirbnbRequirements, HouseRules});
            case CalendarUpdate:
                return Arrays.asList(new DeactivateIBReason[]{PreparationTime, DistantRequest});
            case BetterOffer:
                return Arrays.asList(new DeactivateIBReason[]{TripLength});
            case UnwareIB:
                return Arrays.asList(new DeactivateIBReason[]{UnawareHouseRules, UnawareCalendarUpdated});
            default:
                return Arrays.asList(new DeactivateIBReason[0]);
        }
    }
}
