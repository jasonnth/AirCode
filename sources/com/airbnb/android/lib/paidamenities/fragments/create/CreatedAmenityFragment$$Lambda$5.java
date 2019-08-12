package com.airbnb.android.lib.paidamenities.fragments.create;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CreatedAmenityFragment$$Lambda$5 implements OnClickListener {
    private final CreatedAmenityFragment arg$1;

    private CreatedAmenityFragment$$Lambda$5(CreatedAmenityFragment createdAmenityFragment) {
        this.arg$1 = createdAmenityFragment;
    }

    public static OnClickListener lambdaFactory$(CreatedAmenityFragment createdAmenityFragment) {
        return new CreatedAmenityFragment$$Lambda$5(createdAmenityFragment);
    }

    public void onClick(View view) {
        this.arg$1.launchPaidAmenityTermsOfService();
    }
}
