package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PreapproveInquiryFragment$$Lambda$1 implements Action1 {
    private final PreapproveInquiryFragment arg$1;

    private PreapproveInquiryFragment$$Lambda$1(PreapproveInquiryFragment preapproveInquiryFragment) {
        this.arg$1 = preapproveInquiryFragment;
    }

    public static Action1 lambdaFactory$(PreapproveInquiryFragment preapproveInquiryFragment) {
        return new PreapproveInquiryFragment$$Lambda$1(preapproveInquiryFragment);
    }

    public void call(Object obj) {
        PreapproveInquiryFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
