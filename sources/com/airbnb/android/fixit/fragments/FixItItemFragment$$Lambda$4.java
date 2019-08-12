package com.airbnb.android.fixit.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FixItItemFragment$$Lambda$4 implements OnClickListener {
    private final FixItItemFragment arg$1;

    private FixItItemFragment$$Lambda$4(FixItItemFragment fixItItemFragment) {
        this.arg$1 = fixItItemFragment;
    }

    public static OnClickListener lambdaFactory$(FixItItemFragment fixItItemFragment) {
        return new FixItItemFragment$$Lambda$4(fixItItemFragment);
    }

    public void onClick(View view) {
        this.arg$1.onDonePressed();
    }
}
