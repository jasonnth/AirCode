package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.ResendCohostInvitationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PendingCohostDetailsFragment$$Lambda$3 implements Action1 {
    private final PendingCohostDetailsFragment arg$1;

    private PendingCohostDetailsFragment$$Lambda$3(PendingCohostDetailsFragment pendingCohostDetailsFragment) {
        this.arg$1 = pendingCohostDetailsFragment;
    }

    public static Action1 lambdaFactory$(PendingCohostDetailsFragment pendingCohostDetailsFragment) {
        return new PendingCohostDetailsFragment$$Lambda$3(pendingCohostDetailsFragment);
    }

    public void call(Object obj) {
        PendingCohostDetailsFragment.lambda$new$2(this.arg$1, (ResendCohostInvitationResponse) obj);
    }
}
