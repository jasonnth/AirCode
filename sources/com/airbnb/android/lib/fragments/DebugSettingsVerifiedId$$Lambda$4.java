package com.airbnb.android.lib.fragments;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.airbnb.android.core.utils.DebugSettings.DebugVerification;

final /* synthetic */ class DebugSettingsVerifiedId$$Lambda$4 implements OnCheckedChangeListener {
    private final DebugSettingsVerifiedId arg$1;

    private DebugSettingsVerifiedId$$Lambda$4(DebugSettingsVerifiedId debugSettingsVerifiedId) {
        this.arg$1 = debugSettingsVerifiedId;
    }

    public static OnCheckedChangeListener lambdaFactory$(DebugSettingsVerifiedId debugSettingsVerifiedId) {
        return new DebugSettingsVerifiedId$$Lambda$4(debugSettingsVerifiedId);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.arg$1.mDebugSettings.setUserVerifiedWithVerification(DebugVerification.OFFLINE_ID, z);
    }
}
