package com.airbnb.android.core.enums;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.utils.Check;
import com.facebook.internal.NativeProtocol;
import com.google.common.collect.FluentIterable;

public enum InstantBookingAllowedCategory {
    Off("off"),
    ExperiencedGuests("experienced"),
    EveryOne(NativeProtocol.AUDIENCE_EVERYONE),
    GovernmentId(AccountVerification.SCANID),
    ExperiencedGuestsWithGovernmentId("experienced_guest_with_government_id");
    
    public final String serverKey;

    private InstantBookingAllowedCategory(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public int getLabel() {
        return this == Off ? C0716R.string.no_one : C0716R.string.ml_ib_visibility_meet_requirements;
    }

    public int getDescription() {
        return this == Off ? C0716R.string.ml_ib_visibility_no_one_subtitle : C0716R.string.ml_ib_visibility_meet_requirements_subtitle;
    }

    public int getManageListingLabel() {
        switch (this) {
            case EveryOne:
                return C0716R.string.instant_book_settings_guests_who_meet_requirements;
            case ExperiencedGuestsWithGovernmentId:
            case GovernmentId:
            case ExperiencedGuests:
                return C0716R.string.instant_book_settings_guests_who_meet_additional_requirements;
            default:
                return C0716R.string.ml_ib_visibility_no_one;
        }
    }

    public int getShortDescription() {
        return this == Off ? C0716R.string.no_one : C0716R.string.ml_ib_visibility_experienced;
    }

    public boolean isGovernmentIdNeeded() {
        return this == GovernmentId || this == ExperiencedGuestsWithGovernmentId;
    }

    public boolean isHighRatingNeeded() {
        return this == ExperiencedGuests || this == ExperiencedGuestsWithGovernmentId;
    }

    public boolean isInstantBookEnabled() {
        return this != Off;
    }

    public static InstantBookingAllowedCategory getDefault() {
        return EveryOne;
    }

    public static InstantBookingAllowedCategory fromKey(String key) {
        Check.notNull(key);
        InstantBookingAllowedCategory category = (InstantBookingAllowedCategory) FluentIterable.m1283of(values()).firstMatch(InstantBookingAllowedCategory$$Lambda$1.lambdaFactory$(key)).orNull();
        if (category != null) {
            return category;
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Value is not recognized: " + key));
        return getDefault();
    }

    public static InstantBookingAllowedCategory fromNullableKey(String key) {
        return key == null ? getDefault() : fromKey(key);
    }

    public static InstantBookingAllowedCategory fromListingState(boolean isInstantBookEnabled, boolean isGovernmentIdChecked, boolean isHighRatingChecked) {
        if (!isInstantBookEnabled) {
            return Off;
        }
        if (isGovernmentIdChecked && isHighRatingChecked) {
            return ExperiencedGuestsWithGovernmentId;
        }
        if (isGovernmentIdChecked) {
            return GovernmentId;
        }
        if (isHighRatingChecked) {
            return ExperiencedGuests;
        }
        return EveryOne;
    }
}
