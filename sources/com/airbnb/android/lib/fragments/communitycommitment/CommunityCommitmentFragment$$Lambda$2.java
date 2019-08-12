package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CommunityCommitmentFragment$$Lambda$2 implements Action1 {
    private final CommunityCommitmentFragment arg$1;

    private CommunityCommitmentFragment$$Lambda$2(CommunityCommitmentFragment communityCommitmentFragment) {
        this.arg$1 = communityCommitmentFragment;
    }

    public static Action1 lambdaFactory$(CommunityCommitmentFragment communityCommitmentFragment) {
        return new CommunityCommitmentFragment$$Lambda$2(communityCommitmentFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
