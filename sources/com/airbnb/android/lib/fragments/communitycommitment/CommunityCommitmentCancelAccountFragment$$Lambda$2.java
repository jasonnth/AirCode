package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CommunityCommitmentCancelAccountFragment$$Lambda$2 implements Action1 {
    private final CommunityCommitmentCancelAccountFragment arg$1;

    private CommunityCommitmentCancelAccountFragment$$Lambda$2(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment) {
        this.arg$1 = communityCommitmentCancelAccountFragment;
    }

    public static Action1 lambdaFactory$(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment) {
        return new CommunityCommitmentCancelAccountFragment$$Lambda$2(communityCommitmentCancelAccountFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
