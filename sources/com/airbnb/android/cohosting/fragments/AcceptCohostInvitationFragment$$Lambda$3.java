package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.AcceptCohostInvitationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AcceptCohostInvitationFragment$$Lambda$3 implements Action1 {
    private final AcceptCohostInvitationFragment arg$1;

    private AcceptCohostInvitationFragment$$Lambda$3(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        this.arg$1 = acceptCohostInvitationFragment;
    }

    public static Action1 lambdaFactory$(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        return new AcceptCohostInvitationFragment$$Lambda$3(acceptCohostInvitationFragment);
    }

    public void call(Object obj) {
        AcceptCohostInvitationFragment.lambda$new$2(this.arg$1, (AcceptCohostInvitationResponse) obj);
    }
}
