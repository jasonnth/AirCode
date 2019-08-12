package com.airbnb.android.fixit.fragments;

import com.airbnb.android.core.responses.FixItItemResponse;
import p032rx.functions.Action1;

final /* synthetic */ class FixItItemCommentFragment$$Lambda$1 implements Action1 {
    private final FixItItemCommentFragment arg$1;

    private FixItItemCommentFragment$$Lambda$1(FixItItemCommentFragment fixItItemCommentFragment) {
        this.arg$1 = fixItItemCommentFragment;
    }

    public static Action1 lambdaFactory$(FixItItemCommentFragment fixItItemCommentFragment) {
        return new FixItItemCommentFragment$$Lambda$1(fixItItemCommentFragment);
    }

    public void call(Object obj) {
        FixItItemCommentFragment.lambda$new$0(this.arg$1, (FixItItemResponse) obj);
    }
}
