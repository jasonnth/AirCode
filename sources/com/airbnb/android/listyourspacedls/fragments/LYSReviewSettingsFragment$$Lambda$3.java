package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSReviewSettingsFragment$$Lambda$3 implements OnClickListener {
    private final LYSReviewSettingsFragment arg$1;

    private LYSReviewSettingsFragment$$Lambda$3(LYSReviewSettingsFragment lYSReviewSettingsFragment) {
        this.arg$1 = lYSReviewSettingsFragment;
    }

    public static OnClickListener lambdaFactory$(LYSReviewSettingsFragment lYSReviewSettingsFragment) {
        return new LYSReviewSettingsFragment$$Lambda$3(lYSReviewSettingsFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.showAvailabilityModal();
    }
}
