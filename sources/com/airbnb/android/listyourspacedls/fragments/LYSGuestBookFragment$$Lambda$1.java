package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestBookFragment$$Lambda$1 implements Action1 {
    private final LYSGuestBookFragment arg$1;

    private LYSGuestBookFragment$$Lambda$1(LYSGuestBookFragment lYSGuestBookFragment) {
        this.arg$1 = lYSGuestBookFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestBookFragment lYSGuestBookFragment) {
        return new LYSGuestBookFragment$$Lambda$1(lYSGuestBookFragment);
    }

    public void call(Object obj) {
        LYSGuestBookFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
