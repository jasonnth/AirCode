package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ZenDialog$$Lambda$6 implements OnClickListener {
    private final ZenDialog arg$1;

    private ZenDialog$$Lambda$6(ZenDialog zenDialog) {
        this.arg$1 = zenDialog;
    }

    public static OnClickListener lambdaFactory$(ZenDialog zenDialog) {
        return new ZenDialog$$Lambda$6(zenDialog);
    }

    public void onClick(View view) {
        this.arg$1.dismiss();
    }
}
