package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$27 implements Listener {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$27(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static Listener lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$27(roomsAndGuestsAdapter);
    }

    public void itemSelected(Object obj) {
        RoomsAndGuestsAdapter.lambda$showBathroomPrivacyOptions$24(this.arg$1, (BathroomType) obj);
    }
}
