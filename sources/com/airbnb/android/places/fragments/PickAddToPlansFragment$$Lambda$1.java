package com.airbnb.android.places.fragments;

import com.airbnb.android.places.adapters.TimeOfDayController.OnTimeSelectedListener;
import com.airbnb.android.places.adapters.TimeOfDayController.TimeOfDay;

final /* synthetic */ class PickAddToPlansFragment$$Lambda$1 implements OnTimeSelectedListener {
    private final PickAddToPlansFragment arg$1;

    private PickAddToPlansFragment$$Lambda$1(PickAddToPlansFragment pickAddToPlansFragment) {
        this.arg$1 = pickAddToPlansFragment;
    }

    public static OnTimeSelectedListener lambdaFactory$(PickAddToPlansFragment pickAddToPlansFragment) {
        return new PickAddToPlansFragment$$Lambda$1(pickAddToPlansFragment);
    }

    public void onTimeTapped(TimeOfDay timeOfDay, int i) {
        PickAddToPlansFragment.lambda$new$0(this.arg$1, timeOfDay, i);
    }
}
