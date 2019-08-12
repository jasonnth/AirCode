package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class RemovePreapprovalFragment$$Lambda$1 implements Action1 {
    private final RemovePreapprovalFragment arg$1;

    private RemovePreapprovalFragment$$Lambda$1(RemovePreapprovalFragment removePreapprovalFragment) {
        this.arg$1 = removePreapprovalFragment;
    }

    public static Action1 lambdaFactory$(RemovePreapprovalFragment removePreapprovalFragment) {
        return new RemovePreapprovalFragment$$Lambda$1(removePreapprovalFragment);
    }

    public void call(Object obj) {
        RemovePreapprovalFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
