package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.responses.InquiryResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PreapproveInquiryFragment$$Lambda$3 implements Action1 {
    private final PreapproveInquiryFragment arg$1;

    private PreapproveInquiryFragment$$Lambda$3(PreapproveInquiryFragment preapproveInquiryFragment) {
        this.arg$1 = preapproveInquiryFragment;
    }

    public static Action1 lambdaFactory$(PreapproveInquiryFragment preapproveInquiryFragment) {
        return new PreapproveInquiryFragment$$Lambda$3(preapproveInquiryFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleUpdatedInquiry(TripInformationProvider.create(((InquiryResponse) obj).inquiry));
    }
}
