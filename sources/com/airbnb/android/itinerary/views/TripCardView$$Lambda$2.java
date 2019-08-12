package com.airbnb.android.itinerary.views;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class TripCardView$$Lambda$2 implements OnLongClickListener {
    private final TripCardView arg$1;

    private TripCardView$$Lambda$2(TripCardView tripCardView) {
        this.arg$1 = tripCardView;
    }

    public static OnLongClickListener lambdaFactory$(TripCardView tripCardView) {
        return new TripCardView$$Lambda$2(tripCardView);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.getContext(), this.arg$1.actionTitle.getText().toString());
    }
}
