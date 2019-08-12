package com.airbnb.android.lib.views;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final /* synthetic */ class ExpandableSwitchView$$Lambda$2 implements OnCheckedChangeListener {
    private final ExpandableSwitchView arg$1;

    private ExpandableSwitchView$$Lambda$2(ExpandableSwitchView expandableSwitchView) {
        this.arg$1 = expandableSwitchView;
    }

    public static OnCheckedChangeListener lambdaFactory$(ExpandableSwitchView expandableSwitchView) {
        return new ExpandableSwitchView$$Lambda$2(expandableSwitchView);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ExpandableSwitchView.lambda$setupViews$1(this.arg$1, compoundButton, z);
    }
}
