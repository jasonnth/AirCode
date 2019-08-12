package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ZenDialog$$Lambda$4 implements OnClickListener {
    private final ZenDialog arg$1;

    private ZenDialog$$Lambda$4(ZenDialog zenDialog) {
        this.arg$1 = zenDialog;
    }

    public static OnClickListener lambdaFactory$(ZenDialog zenDialog) {
        return new ZenDialog$$Lambda$4(zenDialog);
    }

    public void onClick(View view) {
        ZenDialog.lambda$onCreateView$3(this.arg$1, view);
    }
}
