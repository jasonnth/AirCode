package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.AccountVerificationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AcceptCohostInvitationFragment$$Lambda$1 implements Action1 {
    private final AcceptCohostInvitationFragment arg$1;

    private AcceptCohostInvitationFragment$$Lambda$1(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        this.arg$1 = acceptCohostInvitationFragment;
    }

    public static Action1 lambdaFactory$(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        return new AcceptCohostInvitationFragment$$Lambda$1(acceptCohostInvitationFragment);
    }

    public void call(Object obj) {
        this.arg$1.launchVerificationFlowIfNeeded(((AccountVerificationsResponse) obj).accountActivationVerifications);
    }
}
