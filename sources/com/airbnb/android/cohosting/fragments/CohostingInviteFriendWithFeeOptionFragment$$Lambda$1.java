package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.SendCohostInvitationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingInviteFriendWithFeeOptionFragment$$Lambda$1 implements Action1 {
    private final CohostingInviteFriendWithFeeOptionFragment arg$1;

    private CohostingInviteFriendWithFeeOptionFragment$$Lambda$1(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment) {
        this.arg$1 = cohostingInviteFriendWithFeeOptionFragment;
    }

    public static Action1 lambdaFactory$(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment) {
        return new CohostingInviteFriendWithFeeOptionFragment$$Lambda$1(cohostingInviteFriendWithFeeOptionFragment);
    }

    public void call(Object obj) {
        CohostingInviteFriendWithFeeOptionFragment.lambda$new$0(this.arg$1, (SendCohostInvitationResponse) obj);
    }
}
