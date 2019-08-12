package com.airbnb.android.cohosting.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class CohostReasonMessageTextInputFragment$$Lambda$1 implements Action1 {
    private final CohostReasonMessageTextInputFragment arg$1;

    private CohostReasonMessageTextInputFragment$$Lambda$1(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        this.arg$1 = cohostReasonMessageTextInputFragment;
    }

    public static Action1 lambdaFactory$(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        return new CohostReasonMessageTextInputFragment$$Lambda$1(cohostReasonMessageTextInputFragment);
    }

    public void call(Object obj) {
        this.arg$1.listener.onSubmitReasons();
    }
}
