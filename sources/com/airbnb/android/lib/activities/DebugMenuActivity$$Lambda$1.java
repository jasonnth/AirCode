package com.airbnb.android.lib.activities;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.airbnb.android.core.utils.DebugSettings.BooleanDebugSetting;

final /* synthetic */ class DebugMenuActivity$$Lambda$1 implements OnCheckedChangeListener {
    private final BooleanDebugSetting arg$1;

    private DebugMenuActivity$$Lambda$1(BooleanDebugSetting booleanDebugSetting) {
        this.arg$1 = booleanDebugSetting;
    }

    public static OnCheckedChangeListener lambdaFactory$(BooleanDebugSetting booleanDebugSetting) {
        return new DebugMenuActivity$$Lambda$1(booleanDebugSetting);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.arg$1.setEnabled(z);
    }
}
