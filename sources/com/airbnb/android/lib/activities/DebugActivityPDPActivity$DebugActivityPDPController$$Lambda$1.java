package com.airbnb.android.lib.activities;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$1 implements OnEditorActionListener {
    private final DebugActivityPDPActivity arg$1;

    private DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$1(DebugActivityPDPActivity debugActivityPDPActivity) {
        this.arg$1 = debugActivityPDPActivity;
    }

    public static OnEditorActionListener lambdaFactory$(DebugActivityPDPActivity debugActivityPDPActivity) {
        return new DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$1(debugActivityPDPActivity);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.arg$1.onEditorAction(textView, i, keyEvent);
    }
}
