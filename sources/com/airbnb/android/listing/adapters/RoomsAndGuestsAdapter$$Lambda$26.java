package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BathroomType;
import p032rx.functions.Func1;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$26 implements Func1 {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$26(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Func1 lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$26(roomsAndGuestsAdapter);
    }

    public Object call(Object obj) {
        return Integer.valueOf(this.arg$1.getBathroomTypeTitleId((BathroomType) obj));
    }
}
