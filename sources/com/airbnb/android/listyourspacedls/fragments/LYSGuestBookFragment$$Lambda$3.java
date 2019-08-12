package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestBookFragment$$Lambda$3 implements Action1 {
    private final LYSGuestBookFragment arg$1;

    private LYSGuestBookFragment$$Lambda$3(LYSGuestBookFragment lYSGuestBookFragment) {
        this.arg$1 = lYSGuestBookFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestBookFragment lYSGuestBookFragment) {
        return new LYSGuestBookFragment$$Lambda$3(lYSGuestBookFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), null);
    }
}
