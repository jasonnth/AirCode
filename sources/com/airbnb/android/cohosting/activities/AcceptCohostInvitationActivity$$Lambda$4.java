package com.airbnb.android.cohosting.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AcceptCohostInvitationActivity$$Lambda$4 implements OnClickListener {
    private final AcceptCohostInvitationActivity arg$1;

    private AcceptCohostInvitationActivity$$Lambda$4(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        this.arg$1 = acceptCohostInvitationActivity;
    }

    public static OnClickListener lambdaFactory$(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        return new AcceptCohostInvitationActivity$$Lambda$4(acceptCohostInvitationActivity);
    }

    public void onClick(View view) {
        this.arg$1.finish();
    }
}
