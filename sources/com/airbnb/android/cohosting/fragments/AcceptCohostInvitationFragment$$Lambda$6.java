package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class AcceptCohostInvitationFragment$$Lambda$6 implements Predicate {
    private final AcceptCohostInvitationFragment arg$1;

    private AcceptCohostInvitationFragment$$Lambda$6(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        this.arg$1 = acceptCohostInvitationFragment;
    }

    public static Predicate lambdaFactory$(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        return new AcceptCohostInvitationFragment$$Lambda$6(acceptCohostInvitationFragment);
    }

    public boolean apply(Object obj) {
        return this.arg$1.isVerificationRequired((AccountVerification) obj);
    }
}
