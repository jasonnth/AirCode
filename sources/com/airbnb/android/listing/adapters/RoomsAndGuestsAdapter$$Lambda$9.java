package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter.Listener;

final /* synthetic */ class RoomsAndGuestsAdapter$$Lambda$9 implements OnClickListener {
    private final Listener arg$1;

    private RoomsAndGuestsAdapter$$Lambda$9(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new RoomsAndGuestsAdapter$$Lambda$9(listener);
    }

    public void onClick(View view) {
        this.arg$1.bedDetails();
    }
}
