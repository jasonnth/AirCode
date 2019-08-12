package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.LegacyBedType;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$20 implements Listener {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$20(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Listener lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$20(roomsAndGuestsAdapter);
    }

    public void itemSelected(Object obj) {
        RoomsAndGuestsAdapter.lambda$showBedTypeOptions$18(this.arg$1, (LegacyBedType) obj);
    }
}
