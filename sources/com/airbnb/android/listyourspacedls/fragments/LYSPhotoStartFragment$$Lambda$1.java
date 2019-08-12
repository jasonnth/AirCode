package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSPhotoStartFragment$$Lambda$1 implements OnClickListener {
    private final LYSPhotoStartFragment arg$1;

    private LYSPhotoStartFragment$$Lambda$1(LYSPhotoStartFragment lYSPhotoStartFragment) {
        this.arg$1 = lYSPhotoStartFragment;
    }

    public static OnClickListener lambdaFactory$(LYSPhotoStartFragment lYSPhotoStartFragment) {
        return new LYSPhotoStartFragment$$Lambda$1(lYSPhotoStartFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipDialog();
    }
}
