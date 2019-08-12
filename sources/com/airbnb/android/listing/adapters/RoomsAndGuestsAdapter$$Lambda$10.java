package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BathroomType;
import com.google.common.base.Predicate;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$10 implements Predicate {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$10(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Predicate lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$10(roomsAndGuestsAdapter);
    }

    public boolean apply(Object obj) {
        return RoomsAndGuestsAdapter.lambda$new$9(this.arg$1, (BathroomType) obj);
    }
}
