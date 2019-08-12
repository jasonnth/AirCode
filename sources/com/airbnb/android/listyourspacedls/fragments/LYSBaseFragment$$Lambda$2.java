package com.airbnb.android.listyourspacedls.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LYSBaseFragment$$Lambda$2 implements OnClickListener {
    private final LYSBaseFragment arg$1;

    private LYSBaseFragment$$Lambda$2(LYSBaseFragment lYSBaseFragment) {
        this.arg$1 = lYSBaseFragment;
    }

    public static OnClickListener lambdaFactory$(LYSBaseFragment lYSBaseFragment) {
        return new LYSBaseFragment$$Lambda$2(lYSBaseFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.onDiscard();
    }
}
