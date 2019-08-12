package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$8 implements OnClickListener {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$8(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static OnClickListener lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$8(roomsAndGuestsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.showBathroomPrivacyOptions();
    }
}
