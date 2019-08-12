package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BathroomType;
import com.google.common.base.Predicate;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$25 implements Predicate {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$25(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Predicate lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$25(roomsAndGuestsAdapter);
    }

    public boolean apply(Object obj) {
        return RoomsAndGuestsAdapter.lambda$showBathroomPrivacyOptions$23(this.arg$1, (BathroomType) obj);
    }
}
