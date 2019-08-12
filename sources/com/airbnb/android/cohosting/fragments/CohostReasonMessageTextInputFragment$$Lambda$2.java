package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostReasonMessageTextInputFragment$$Lambda$2 implements Action1 {
    private final CohostReasonMessageTextInputFragment arg$1;

    private CohostReasonMessageTextInputFragment$$Lambda$2(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        this.arg$1 = cohostReasonMessageTextInputFragment;
    }

    public static Action1 lambdaFactory$(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        return new CohostReasonMessageTextInputFragment$$Lambda$2(cohostReasonMessageTextInputFragment);
    }

    public void call(Object obj) {
        CohostReasonMessageTextInputFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
