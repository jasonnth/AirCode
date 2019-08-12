package com.airbnb.android.fixit.fragments;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class FixItItemCommentFragment$$Lambda$4 implements Listener {
    private final FixItItemCommentFragment arg$1;

    private FixItItemCommentFragment$$Lambda$4(FixItItemCommentFragment fixItItemCommentFragment) {
        this.arg$1 = fixItItemCommentFragment;
    }

    public static Listener lambdaFactory$(FixItItemCommentFragment fixItItemCommentFragment) {
        return new FixItItemCommentFragment$$Lambda$4(fixItItemCommentFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.enableSaveButton(z);
    }
}
