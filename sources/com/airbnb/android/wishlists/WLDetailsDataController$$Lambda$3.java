package com.airbnb.android.wishlists;

import p032rx.functions.Action1;

final /* synthetic */ class WLDetailsDataController$$Lambda$3 implements Action1 {
    private final WLDetailsDataController arg$1;

    private WLDetailsDataController$$Lambda$3(WLDetailsDataController wLDetailsDataController) {
        this.arg$1 = wLDetailsDataController;
    }

    public static Action1 lambdaFactory$(WLDetailsDataController wLDetailsDataController) {
        return new WLDetailsDataController$$Lambda$3(wLDetailsDataController);
    }

    public void call(Object obj) {
        this.arg$1.notifyListeners();
    }
}