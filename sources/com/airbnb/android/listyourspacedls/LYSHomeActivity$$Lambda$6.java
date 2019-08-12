package com.airbnb.android.listyourspacedls;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSHomeActivity$$Lambda$6 implements OnClickListener {
    private final LYSHomeActivity arg$1;
    private final long arg$2;

    private LYSHomeActivity$$Lambda$6(LYSHomeActivity lYSHomeActivity, long j) {
        this.arg$1 = lYSHomeActivity;
        this.arg$2 = j;
    }

    public static OnClickListener lambdaFactory$(LYSHomeActivity lYSHomeActivity, long j) {
        return new LYSHomeActivity$$Lambda$6(lYSHomeActivity, j);
    }

    public void onClick(View view) {
        this.arg$1.executeBatchRequest(this.arg$2);
    }
}
