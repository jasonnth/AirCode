package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LocationAdapter$$Lambda$5 implements OnClickListener {
    private final Listener arg$1;

    private LocationAdapter$$Lambda$5(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new LocationAdapter$$Lambda$5(listener);
    }

    public void onClick(View view) {
        this.arg$1.exactLocationMap();
    }
}
