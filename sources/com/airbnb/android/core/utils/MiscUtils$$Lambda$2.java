package com.airbnb.android.core.utils;

import android.view.View;

final /* synthetic */ class MiscUtils$$Lambda$2 implements Runnable {
    private final View arg$1;

    private MiscUtils$$Lambda$2(View view) {
        this.arg$1 = view;
    }

    public static Runnable lambdaFactory$(View view) {
        return new MiscUtils$$Lambda$2(view);
    }

    public void run() {
        this.arg$1.setPressed(false);
    }
}
