package com.airbnb.android.cohosting.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AcceptCohostInvitationFragment$$Lambda$5 implements OnClickListener {
    private final AcceptCohostInvitationFragment arg$1;

    private AcceptCohostInvitationFragment$$Lambda$5(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        this.arg$1 = acceptCohostInvitationFragment;
    }

    public static OnClickListener lambdaFactory$(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        return new AcceptCohostInvitationFragment$$Lambda$5(acceptCohostInvitationFragment);
    }

    public void onClick(View view) {
        this.arg$1.verifyUserAndAccept();
    }
}
