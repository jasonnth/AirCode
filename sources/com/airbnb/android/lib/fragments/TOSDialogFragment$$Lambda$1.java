package com.airbnb.android.lib.fragments;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final /* synthetic */ class TOSDialogFragment$$Lambda$1 implements OnCheckedChangeListener {
    private final TOSDialogFragment arg$1;

    private TOSDialogFragment$$Lambda$1(TOSDialogFragment tOSDialogFragment) {
        this.arg$1 = tOSDialogFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(TOSDialogFragment tOSDialogFragment) {
        return new TOSDialogFragment$$Lambda$1(tOSDialogFragment);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.arg$1.updatedButtons();
    }
}
