package com.airbnb.android.cohosting.activities;

import com.airbnb.android.cohosting.executors.CohostInvitationActionExecutor;
import com.airbnb.android.cohosting.fragments.ConfirmInvitationAcceptedFragment;

final /* synthetic */ class AcceptCohostInvitationActivity$$Lambda$3 implements CohostInvitationActionExecutor {
    private final AcceptCohostInvitationActivity arg$1;

    private AcceptCohostInvitationActivity$$Lambda$3(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        this.arg$1 = acceptCohostInvitationActivity;
    }

    public static CohostInvitationActionExecutor lambdaFactory$(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        return new AcceptCohostInvitationActivity$$Lambda$3(acceptCohostInvitationActivity);
    }

    public void displayInvitationConfirmationPage() {
        this.arg$1.showFragment(ConfirmInvitationAcceptedFragment.create());
    }
}
