package com.airbnb.android.listyourspacedls.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LYSBaseFragment$$Lambda$3 implements OnClickListener {
    private final LYSBaseFragment arg$1;

    private LYSBaseFragment$$Lambda$3(LYSBaseFragment lYSBaseFragment) {
        this.arg$1 = lYSBaseFragment;
    }

    public static OnClickListener lambdaFactory$(LYSBaseFragment lYSBaseFragment) {
        return new LYSBaseFragment$$Lambda$3(lYSBaseFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        LYSBaseFragment.lambda$onBackPressed$1(this.arg$1, dialogInterface, i);
    }
}
