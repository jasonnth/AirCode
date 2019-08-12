package com.airbnb.android.fixit.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class FixItItemCommentFragment$$Lambda$2 implements Action1 {
    private final FixItItemCommentFragment arg$1;

    private FixItItemCommentFragment$$Lambda$2(FixItItemCommentFragment fixItItemCommentFragment) {
        this.arg$1 = fixItItemCommentFragment;
    }

    public static Action1 lambdaFactory$(FixItItemCommentFragment fixItItemCommentFragment) {
        return new FixItItemCommentFragment$$Lambda$2(fixItItemCommentFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
