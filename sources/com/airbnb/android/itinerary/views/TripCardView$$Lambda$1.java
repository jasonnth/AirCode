package com.airbnb.android.itinerary.views;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class TripCardView$$Lambda$1 implements OnLongClickListener {
    private final TripCardView arg$1;
    private final int arg$2;

    private TripCardView$$Lambda$1(TripCardView tripCardView, int i) {
        this.arg$1 = tripCardView;
        this.arg$2 = i;
    }

    public static OnLongClickListener lambdaFactory$(TripCardView tripCardView, int i) {
        return new TripCardView$$Lambda$1(tripCardView, i);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.getContext(), this.arg$1.subtitle.getText().toString(), this.arg$2);
    }
}
