package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class CohostReasonPrivateFeedbackTextInputFragment$$Lambda$2 implements Listener {
    private final CohostReasonPrivateFeedbackTextInputFragment arg$1;

    private CohostReasonPrivateFeedbackTextInputFragment$$Lambda$2(CohostReasonPrivateFeedbackTextInputFragment cohostReasonPrivateFeedbackTextInputFragment) {
        this.arg$1 = cohostReasonPrivateFeedbackTextInputFragment;
    }

    public static Listener lambdaFactory$(CohostReasonPrivateFeedbackTextInputFragment cohostReasonPrivateFeedbackTextInputFragment) {
        return new CohostReasonPrivateFeedbackTextInputFragment$$Lambda$2(cohostReasonPrivateFeedbackTextInputFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.button.setEnabled(z);
    }
}
