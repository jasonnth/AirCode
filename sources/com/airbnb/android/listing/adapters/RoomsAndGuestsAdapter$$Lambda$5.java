package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$5 implements OnClickListener {
    private final RoomsAndGuestsAdapter arg$1;

    private RoomsAndGuestsAdapter$$Lambda$5(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        this.arg$1 = roomsAndGuestsAdapter;
    }

    public static OnClickListener lambdaFactory$(RoomsAndGuestsAdapter roomsAndGuestsAdapter) {
        return new RoomsAndGuestsAdapter$$Lambda$5(roomsAndGuestsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.showBedTypeOptions();
    }
}