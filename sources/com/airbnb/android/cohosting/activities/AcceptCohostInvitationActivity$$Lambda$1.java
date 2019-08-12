package com.airbnb.android.cohosting.activities;

import com.airbnb.android.core.responses.CohostInvitationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AcceptCohostInvitationActivity$$Lambda$1 implements Action1 {
    private final AcceptCohostInvitationActivity arg$1;

    private AcceptCohostInvitationActivity$$Lambda$1(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        this.arg$1 = acceptCohostInvitationActivity;
    }

    public static Action1 lambdaFactory$(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        return new AcceptCohostInvitationActivity$$Lambda$1(acceptCohostInvitationActivity);
    }

    public void call(Object obj) {
        AcceptCohostInvitationActivity.lambda$new$0(this.arg$1, (CohostInvitationResponse) obj);
    }
}
