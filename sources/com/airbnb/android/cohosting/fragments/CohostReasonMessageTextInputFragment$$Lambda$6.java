package com.airbnb.android.cohosting.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostReasonMessageTextInputFragment$$Lambda$6 implements OnClickListener {
    private final CohostReasonMessageTextInputFragment arg$1;

    private CohostReasonMessageTextInputFragment$$Lambda$6(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        this.arg$1 = cohostReasonMessageTextInputFragment;
    }

    public static OnClickListener lambdaFactory$(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        return new CohostReasonMessageTextInputFragment$$Lambda$6(cohostReasonMessageTextInputFragment);
    }

    public void onClick(View view) {
        this.arg$1.submitReasons(this.arg$1.editTextPageView.getText().toString());
    }
}
