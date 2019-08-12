package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.DeleteCohostInvitationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PendingCohostDetailsFragment$$Lambda$1 implements Action1 {
    private final PendingCohostDetailsFragment arg$1;

    private PendingCohostDetailsFragment$$Lambda$1(PendingCohostDetailsFragment pendingCohostDetailsFragment) {
        this.arg$1 = pendingCohostDetailsFragment;
    }

    public static Action1 lambdaFactory$(PendingCohostDetailsFragment pendingCohostDetailsFragment) {
        return new PendingCohostDetailsFragment$$Lambda$1(pendingCohostDetailsFragment);
    }

    public void call(Object obj) {
        PendingCohostDetailsFragment.lambda$new$0(this.arg$1, (DeleteCohostInvitationResponse) obj);
    }
}
