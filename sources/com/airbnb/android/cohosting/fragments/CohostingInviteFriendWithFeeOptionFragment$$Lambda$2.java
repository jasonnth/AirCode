package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingInviteFriendWithFeeOptionFragment$$Lambda$2 implements Action1 {
    private final CohostingInviteFriendWithFeeOptionFragment arg$1;

    private CohostingInviteFriendWithFeeOptionFragment$$Lambda$2(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment) {
        this.arg$1 = cohostingInviteFriendWithFeeOptionFragment;
    }

    public static Action1 lambdaFactory$(CohostingInviteFriendWithFeeOptionFragment cohostingInviteFriendWithFeeOptionFragment) {
        return new CohostingInviteFriendWithFeeOptionFragment$$Lambda$2(cohostingInviteFriendWithFeeOptionFragment);
    }

    public void call(Object obj) {
        CohostingInviteFriendWithFeeOptionFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
