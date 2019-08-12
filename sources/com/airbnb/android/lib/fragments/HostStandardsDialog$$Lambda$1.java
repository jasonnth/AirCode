package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostStandardsDialog$$Lambda$1 implements OnClickListener {
    private final HostStandardsDialog arg$1;

    private HostStandardsDialog$$Lambda$1(HostStandardsDialog hostStandardsDialog) {
        this.arg$1 = hostStandardsDialog;
    }

    public static OnClickListener lambdaFactory$(HostStandardsDialog hostStandardsDialog) {
        return new HostStandardsDialog$$Lambda$1(hostStandardsDialog);
    }

    public void onClick(View view) {
        this.arg$1.dismiss();
    }
}
