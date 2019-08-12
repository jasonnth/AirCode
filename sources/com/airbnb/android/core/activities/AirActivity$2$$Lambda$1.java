package com.airbnb.android.core.activities;

import android.support.p000v4.app.Fragment;
import p032rx.functions.Action0;

final /* synthetic */ class AirActivity$2$$Lambda$1 implements Action0 {
    private final C58042 arg$1;
    private final Fragment arg$2;

    private AirActivity$2$$Lambda$1(C58042 r1, Fragment fragment) {
        this.arg$1 = r1;
        this.arg$2 = fragment;
    }

    public static Action0 lambdaFactory$(C58042 r1, Fragment fragment) {
        return new AirActivity$2$$Lambda$1(r1, fragment);
    }

    public void call() {
        this.arg$1.showIfReactIsInitialized(this.arg$2);
    }
}
