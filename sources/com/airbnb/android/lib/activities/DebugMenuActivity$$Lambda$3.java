package com.airbnb.android.lib.activities;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$3 implements OnCheckedChangeListener {
    private final boolean[] arg$1;

    private DebugMenuActivity$$Lambda$3(boolean[] zArr) {
        this.arg$1 = zArr;
    }

    public static OnCheckedChangeListener lambdaFactory$(boolean[] zArr) {
        return new DebugMenuActivity$$Lambda$3(zArr);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        DebugMenuActivity.lambda$onClickGoToExperience$2(this.arg$1, compoundButton, z);
    }
}
