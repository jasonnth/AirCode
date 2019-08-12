package com.airbnb.android.guestrecovery.adapter;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestRecoveryEpoxyController$$Lambda$4 implements OnClickListener {
    private final GuestRecoveryEpoxyController arg$1;

    private GuestRecoveryEpoxyController$$Lambda$4(GuestRecoveryEpoxyController guestRecoveryEpoxyController) {
        this.arg$1 = guestRecoveryEpoxyController;
    }

    public static OnClickListener lambdaFactory$(GuestRecoveryEpoxyController guestRecoveryEpoxyController) {
        return new GuestRecoveryEpoxyController$$Lambda$4(guestRecoveryEpoxyController);
    }

    public void onClick(View view) {
        GuestRecoveryEpoxyController.lambda$populateGoToTripDetailsModel$1(this.arg$1, view);
    }
}
