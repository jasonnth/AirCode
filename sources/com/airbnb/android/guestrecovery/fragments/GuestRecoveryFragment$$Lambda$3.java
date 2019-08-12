package com.airbnb.android.guestrecovery.fragments;

import com.airbnb.android.core.responses.ListingsReplacementResponse;
import p032rx.functions.Action1;

final /* synthetic */ class GuestRecoveryFragment$$Lambda$3 implements Action1 {
    private final GuestRecoveryFragment arg$1;

    private GuestRecoveryFragment$$Lambda$3(GuestRecoveryFragment guestRecoveryFragment) {
        this.arg$1 = guestRecoveryFragment;
    }

    public static Action1 lambdaFactory$(GuestRecoveryFragment guestRecoveryFragment) {
        return new GuestRecoveryFragment$$Lambda$3(guestRecoveryFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleSimilarListingSuccess(((ListingsReplacementResponse) obj).listingReplacements);
    }
}
