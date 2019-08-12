package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ExpandableSwitchView$$Lambda$1 implements OnClickListener {
    private final ExpandableSwitchView arg$1;

    private ExpandableSwitchView$$Lambda$1(ExpandableSwitchView expandableSwitchView) {
        this.arg$1 = expandableSwitchView;
    }

    public static OnClickListener lambdaFactory$(ExpandableSwitchView expandableSwitchView) {
        return new ExpandableSwitchView$$Lambda$1(expandableSwitchView);
    }

    public void onClick(View view) {
        ExpandableSwitchView.lambda$new$0(this.arg$1, view);
    }
}
