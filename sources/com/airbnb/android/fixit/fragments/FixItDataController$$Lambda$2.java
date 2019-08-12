package com.airbnb.android.fixit.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class FixItDataController$$Lambda$2 implements Action1 {
    private static final FixItDataController$$Lambda$2 instance = new FixItDataController$$Lambda$2();

    private FixItDataController$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataUpdated();
    }
}
