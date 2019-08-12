package com.airbnb.android.fixit.fragments;

import com.airbnb.android.core.responses.FixItItemResponse;
import p032rx.functions.Action1;

final /* synthetic */ class FixItItemFragment$$Lambda$1 implements Action1 {
    private final FixItItemFragment arg$1;

    private FixItItemFragment$$Lambda$1(FixItItemFragment fixItItemFragment) {
        this.arg$1 = fixItItemFragment;
    }

    public static Action1 lambdaFactory$(FixItItemFragment fixItItemFragment) {
        return new FixItItemFragment$$Lambda$1(fixItItemFragment);
    }

    public void call(Object obj) {
        this.arg$1.dataController.setItem(((FixItItemResponse) obj).item);
    }
}
