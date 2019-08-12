package com.airbnb.android.cohosting.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostReasonPrivateFeedbackTextInputFragment$$Lambda$3 implements OnClickListener {
    private final CohostReasonPrivateFeedbackTextInputFragment arg$1;

    private CohostReasonPrivateFeedbackTextInputFragment$$Lambda$3(CohostReasonPrivateFeedbackTextInputFragment cohostReasonPrivateFeedbackTextInputFragment) {
        this.arg$1 = cohostReasonPrivateFeedbackTextInputFragment;
    }

    public static OnClickListener lambdaFactory$(CohostReasonPrivateFeedbackTextInputFragment cohostReasonPrivateFeedbackTextInputFragment) {
        return new CohostReasonPrivateFeedbackTextInputFragment$$Lambda$3(cohostReasonPrivateFeedbackTextInputFragment);
    }

    public void onClick(View view) {
        this.arg$1.listener.onSubmitPrivateFeedbackInput(this.arg$1.editTextPageView.getText().toString());
    }
}
