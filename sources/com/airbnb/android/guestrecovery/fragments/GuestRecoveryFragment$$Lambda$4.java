package com.airbnb.android.guestrecovery.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class GuestRecoveryFragment$$Lambda$4 implements Action1 {
    private final GuestRecoveryFragment arg$1;

    private GuestRecoveryFragment$$Lambda$4(GuestRecoveryFragment guestRecoveryFragment) {
        this.arg$1 = guestRecoveryFragment;
    }

    public static Action1 lambdaFactory$(GuestRecoveryFragment guestRecoveryFragment) {
        return new GuestRecoveryFragment$$Lambda$4(guestRecoveryFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleError((AirRequestNetworkException) obj);
    }
}
