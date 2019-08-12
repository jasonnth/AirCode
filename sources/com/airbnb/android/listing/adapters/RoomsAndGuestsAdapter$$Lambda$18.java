package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$18 implements Listener {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$18(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Listener lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$18(roomsAndGuestsAdapter);
    }

    public void itemSelected(Object obj) {
        RoomsAndGuestsAdapter.lambda$showBedCountOptions$17(this.arg$1, (Integer) obj);
    }
}
