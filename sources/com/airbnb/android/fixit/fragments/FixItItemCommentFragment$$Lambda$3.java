package com.airbnb.android.fixit.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class FixItItemCommentFragment$$Lambda$3 implements Action1 {
    private final FixItItemCommentFragment arg$1;

    private FixItItemCommentFragment$$Lambda$3(FixItItemCommentFragment fixItItemCommentFragment) {
        this.arg$1 = fixItItemCommentFragment;
    }

    public static Action1 lambdaFactory$(FixItItemCommentFragment fixItItemCommentFragment) {
        return new FixItItemCommentFragment$$Lambda$3(fixItItemCommentFragment);
    }

    public void call(Object obj) {
        this.arg$1.footer.setButtonLoading(false);
    }
}
