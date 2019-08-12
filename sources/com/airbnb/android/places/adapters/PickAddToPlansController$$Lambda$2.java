package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;

final /* synthetic */ class PickAddToPlansController$$Lambda$2 implements OnClickListener {
    private final PickAddToPlansController arg$1;
    private final AirDate arg$2;
    private final int arg$3;

    private PickAddToPlansController$$Lambda$2(PickAddToPlansController pickAddToPlansController, AirDate airDate, int i) {
        this.arg$1 = pickAddToPlansController;
        this.arg$2 = airDate;
        this.arg$3 = i;
    }

    public static OnClickListener lambdaFactory$(PickAddToPlansController pickAddToPlansController, AirDate airDate, int i) {
        return new PickAddToPlansController$$Lambda$2(pickAddToPlansController, airDate, i);
    }

    public void onClick(View view) {
        this.arg$1.onPlanSelectedListener.onDateTapped(this.arg$2, this.arg$3);
    }
}
