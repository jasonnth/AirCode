package com.airbnb.android.cohosting.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostingServicesIntroFragment$$Lambda$1 implements OnClickListener {
    private final CohostingServicesIntroFragment arg$1;

    private CohostingServicesIntroFragment$$Lambda$1(CohostingServicesIntroFragment cohostingServicesIntroFragment) {
        this.arg$1 = cohostingServicesIntroFragment;
    }

    public static OnClickListener lambdaFactory$(CohostingServicesIntroFragment cohostingServicesIntroFragment) {
        return new CohostingServicesIntroFragment$$Lambda$1(cohostingServicesIntroFragment);
    }

    public void onClick(View view) {
        this.arg$1.onBackPressed();
    }
}
