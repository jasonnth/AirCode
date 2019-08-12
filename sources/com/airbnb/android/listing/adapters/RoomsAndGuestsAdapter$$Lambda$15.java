package com.airbnb.android.listing.adapters;

import com.airbnb.android.listing.utils.ListingTextUtils;
import p032rx.functions.Func1;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$15 implements Func1 {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$15(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Func1 lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$15(roomsAndGuestsAdapter);
    }

    public Object call(Object obj) {
        return ListingTextUtils.createBedroomCount(this.arg$1.context, ((Integer) obj).intValue());
    }
}
