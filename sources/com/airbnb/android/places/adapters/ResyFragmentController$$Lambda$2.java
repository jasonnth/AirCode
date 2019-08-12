package com.airbnb.android.places.adapters;

import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

final /* synthetic */ class ResyFragmentController$$Lambda$2 implements OnValueChangedListener {
    private final ResyFragmentController arg$1;

    private ResyFragmentController$$Lambda$2(ResyFragmentController resyFragmentController) {
        this.arg$1 = resyFragmentController;
    }

    public static OnValueChangedListener lambdaFactory$(ResyFragmentController resyFragmentController) {
        return new ResyFragmentController$$Lambda$2(resyFragmentController);
    }

    public void onValueChanged(int i, int i2) {
        this.arg$1.resyController.updateGuests(i2);
    }
}
