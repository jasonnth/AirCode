package com.airbnb.android.guestrecovery.fragments;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class GuestRecoveryFragment$$Lambda$1 implements Action1 {
    private final GuestRecoveryFragment arg$1;

    private GuestRecoveryFragment$$Lambda$1(GuestRecoveryFragment guestRecoveryFragment) {
        this.arg$1 = guestRecoveryFragment;
    }

    public static Action1 lambdaFactory$(GuestRecoveryFragment guestRecoveryFragment) {
        return new GuestRecoveryFragment$$Lambda$1(guestRecoveryFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleReservationSuccess(((ReservationResponse) obj).reservation);
    }
}
