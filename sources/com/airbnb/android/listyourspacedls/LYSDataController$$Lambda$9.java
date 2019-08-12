package com.airbnb.android.listyourspacedls;

import com.airbnb.android.listyourspacedls.LYSDataController.UpdateListener;
import p032rx.functions.Action1;

final /* synthetic */ class LYSDataController$$Lambda$9 implements Action1 {
    private static final LYSDataController$$Lambda$9 instance = new LYSDataController$$Lambda$9();

    private LYSDataController$$Lambda$9() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataUpdated();
    }
}
