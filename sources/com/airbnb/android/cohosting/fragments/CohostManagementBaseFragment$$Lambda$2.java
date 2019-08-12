package com.airbnb.android.cohosting.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class CohostManagementBaseFragment$$Lambda$2 implements OnClickListener {
    private final CohostManagementBaseFragment arg$1;

    private CohostManagementBaseFragment$$Lambda$2(CohostManagementBaseFragment cohostManagementBaseFragment) {
        this.arg$1 = cohostManagementBaseFragment;
    }

    public static OnClickListener lambdaFactory$(CohostManagementBaseFragment cohostManagementBaseFragment) {
        return new CohostManagementBaseFragment$$Lambda$2(cohostManagementBaseFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.onUnsavedChangesDiscarded();
    }
}
