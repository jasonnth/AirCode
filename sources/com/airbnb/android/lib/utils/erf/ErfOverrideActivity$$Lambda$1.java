package com.airbnb.android.lib.utils.erf;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ErfOverrideActivity$$Lambda$1 implements OnClickListener {
    private final ErfOverrideActivity arg$1;

    private ErfOverrideActivity$$Lambda$1(ErfOverrideActivity erfOverrideActivity) {
        this.arg$1 = erfOverrideActivity;
    }

    public static OnClickListener lambdaFactory$(ErfOverrideActivity erfOverrideActivity) {
        return new ErfOverrideActivity$$Lambda$1(erfOverrideActivity);
    }

    public void onClick(View view) {
        this.arg$1.refreshExperiments();
    }
}
