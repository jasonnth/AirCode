package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class CohostReasonMessageTextInputFragment$$Lambda$4 implements Listener {
    private final CohostReasonMessageTextInputFragment arg$1;

    private CohostReasonMessageTextInputFragment$$Lambda$4(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        this.arg$1 = cohostReasonMessageTextInputFragment;
    }

    public static Listener lambdaFactory$(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment) {
        return new CohostReasonMessageTextInputFragment$$Lambda$4(cohostReasonMessageTextInputFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.button.setEnabled(z);
    }
}
