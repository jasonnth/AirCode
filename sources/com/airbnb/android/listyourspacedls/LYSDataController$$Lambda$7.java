package com.airbnb.android.listyourspacedls;

import com.airbnb.android.listyourspacedls.LYSDataController.UpdateListener;
import p032rx.functions.Action1;

final /* synthetic */ class LYSDataController$$Lambda$7 implements Action1 {
    private static final LYSDataController$$Lambda$7 instance = new LYSDataController$$Lambda$7();

    private LYSDataController$$Lambda$7() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataUpdated();
    }
}
