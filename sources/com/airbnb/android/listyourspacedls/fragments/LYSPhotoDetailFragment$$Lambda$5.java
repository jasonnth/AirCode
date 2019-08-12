package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSPhotoDetailFragment$$Lambda$5 implements OnClickListener {
    private final LYSPhotoDetailFragment arg$1;

    private LYSPhotoDetailFragment$$Lambda$5(LYSPhotoDetailFragment lYSPhotoDetailFragment) {
        this.arg$1 = lYSPhotoDetailFragment;
    }

    public static OnClickListener lambdaFactory$(LYSPhotoDetailFragment lYSPhotoDetailFragment) {
        return new LYSPhotoDetailFragment$$Lambda$5(lYSPhotoDetailFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipDialog();
    }
}
