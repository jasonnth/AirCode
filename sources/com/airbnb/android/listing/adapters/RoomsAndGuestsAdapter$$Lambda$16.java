package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$16 implements Listener {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$16(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Listener lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$16(roomsAndGuestsAdapter);
    }

    public void itemSelected(Object obj) {
        RoomsAndGuestsAdapter.lambda$showBedroomCountOptions$15(this.arg$1, (Integer) obj);
    }
}
