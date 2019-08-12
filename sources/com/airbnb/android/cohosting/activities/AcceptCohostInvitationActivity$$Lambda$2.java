package com.airbnb.android.cohosting.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AcceptCohostInvitationActivity$$Lambda$2 implements Action1 {
    private final AcceptCohostInvitationActivity arg$1;

    private AcceptCohostInvitationActivity$$Lambda$2(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        this.arg$1 = acceptCohostInvitationActivity;
    }

    public static Action1 lambdaFactory$(AcceptCohostInvitationActivity acceptCohostInvitationActivity) {
        return new AcceptCohostInvitationActivity$$Lambda$2(acceptCohostInvitationActivity);
    }

    public void call(Object obj) {
        AcceptCohostInvitationActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
