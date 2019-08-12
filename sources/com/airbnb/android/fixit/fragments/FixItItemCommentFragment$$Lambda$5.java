package com.airbnb.android.fixit.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FixItItemCommentFragment$$Lambda$5 implements OnClickListener {
    private final FixItItemCommentFragment arg$1;

    private FixItItemCommentFragment$$Lambda$5(FixItItemCommentFragment fixItItemCommentFragment) {
        this.arg$1 = fixItItemCommentFragment;
    }

    public static OnClickListener lambdaFactory$(FixItItemCommentFragment fixItItemCommentFragment) {
        return new FixItItemCommentFragment$$Lambda$5(fixItItemCommentFragment);
    }

    public void onClick(View view) {
        this.arg$1.onSave();
    }
}
