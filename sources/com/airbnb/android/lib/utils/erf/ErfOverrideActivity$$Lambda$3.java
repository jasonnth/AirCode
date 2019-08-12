package com.airbnb.android.lib.utils.erf;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.utils.erf.ErfOverrideActivity.AddExperimentDialog;

final /* synthetic */ class ErfOverrideActivity$$Lambda$3 implements OnClickListener {
    private final ErfOverrideActivity arg$1;

    private ErfOverrideActivity$$Lambda$3(ErfOverrideActivity erfOverrideActivity) {
        this.arg$1 = erfOverrideActivity;
    }

    public static OnClickListener lambdaFactory$(ErfOverrideActivity erfOverrideActivity) {
        return new ErfOverrideActivity$$Lambda$3(erfOverrideActivity);
    }

    public void onClick(View view) {
        AddExperimentDialog.newInstance().showAllowingStateLoss(this.arg$1.getSupportFragmentManager(), null);
    }
}
