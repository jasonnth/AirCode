package com.airbnb.android.listing.adapters;

import com.airbnb.android.listing.utils.ListingTextUtils;
import p032rx.functions.Func1;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$21 implements Func1 {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$21(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Func1 lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$21(roomsAndGuestsAdapter);
    }

    public Object call(Object obj) {
        return ListingTextUtils.createCapacityCount(this.arg$1.context, ((Integer) obj).intValue());
    }
}
