package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AcceptCohostInvitationFragment$$Lambda$4 implements Action1 {
    private final AcceptCohostInvitationFragment arg$1;

    private AcceptCohostInvitationFragment$$Lambda$4(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        this.arg$1 = acceptCohostInvitationFragment;
    }

    public static Action1 lambdaFactory$(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        return new AcceptCohostInvitationFragment$$Lambda$4(acceptCohostInvitationFragment);
    }

    public void call(Object obj) {
        AcceptCohostInvitationFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
