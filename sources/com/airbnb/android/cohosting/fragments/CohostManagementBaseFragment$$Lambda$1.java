package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class CohostManagementBaseFragment$$Lambda$1 implements OnBackListener {
    private final CohostManagementBaseFragment arg$1;

    private CohostManagementBaseFragment$$Lambda$1(CohostManagementBaseFragment cohostManagementBaseFragment) {
        this.arg$1 = cohostManagementBaseFragment;
    }

    public static OnBackListener lambdaFactory$(CohostManagementBaseFragment cohostManagementBaseFragment) {
        return new CohostManagementBaseFragment$$Lambda$1(cohostManagementBaseFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.onBackPressed();
    }
}
