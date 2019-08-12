package com.airbnb.android.cohosting.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostManagementActivity$$Lambda$3 implements OnClickListener {
    private final CohostManagementActivity arg$1;

    private CohostManagementActivity$$Lambda$3(CohostManagementActivity cohostManagementActivity) {
        this.arg$1 = cohostManagementActivity;
    }

    public static OnClickListener lambdaFactory$(CohostManagementActivity cohostManagementActivity) {
        return new CohostManagementActivity$$Lambda$3(cohostManagementActivity);
    }

    public void onClick(View view) {
        this.arg$1.finish();
    }
}
