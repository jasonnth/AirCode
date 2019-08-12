package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PreapproveInquiryFragment$$Lambda$4 implements Action1 {
    private final PreapproveInquiryFragment arg$1;

    private PreapproveInquiryFragment$$Lambda$4(PreapproveInquiryFragment preapproveInquiryFragment) {
        this.arg$1 = preapproveInquiryFragment;
    }

    public static Action1 lambdaFactory$(PreapproveInquiryFragment preapproveInquiryFragment) {
        return new PreapproveInquiryFragment$$Lambda$4(preapproveInquiryFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleError((AirRequestNetworkException) obj);
    }
}
