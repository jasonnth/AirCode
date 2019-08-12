package com.airbnb.android.guestrecovery.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestRecoveryFragment$$Lambda$5 implements OnClickListener {
    private final GuestRecoveryFragment arg$1;

    private GuestRecoveryFragment$$Lambda$5(GuestRecoveryFragment guestRecoveryFragment) {
        this.arg$1 = guestRecoveryFragment;
    }

    public static OnClickListener lambdaFactory$(GuestRecoveryFragment guestRecoveryFragment) {
        return new GuestRecoveryFragment$$Lambda$5(guestRecoveryFragment);
    }

    public void onClick(View view) {
        this.arg$1.fetchReservation();
    }
}
