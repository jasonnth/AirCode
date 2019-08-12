package com.airbnb.android.listing.fragments;

import android.animation.Animator;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory.AnimatorStepListener;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$9 implements AnimatorStepListener {
    private final AddressAutoCompleteFragment arg$1;
    private final boolean arg$2;

    private AddressAutoCompleteFragment$$Lambda$9(AddressAutoCompleteFragment addressAutoCompleteFragment, boolean z) {
        this.arg$1 = addressAutoCompleteFragment;
        this.arg$2 = z;
    }

    public static AnimatorStepListener lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment, boolean z) {
        return new AddressAutoCompleteFragment$$Lambda$9(addressAutoCompleteFragment, z);
    }

    public void onAnimatorEvent(Animator animator) {
        AddressAutoCompleteFragment.lambda$showLoadingOverlay$7(this.arg$1, this.arg$2, animator);
    }
}
