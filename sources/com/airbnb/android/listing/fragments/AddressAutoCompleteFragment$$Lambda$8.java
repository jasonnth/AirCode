package com.airbnb.android.listing.fragments;

import android.animation.Animator;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory.AnimatorStepListener;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$8 implements AnimatorStepListener {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$8(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static AnimatorStepListener lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$8(addressAutoCompleteFragment);
    }

    public void onAnimatorEvent(Animator animator) {
        AddressAutoCompleteFragment.lambda$showLoadingOverlay$6(this.arg$1, animator);
    }
}
