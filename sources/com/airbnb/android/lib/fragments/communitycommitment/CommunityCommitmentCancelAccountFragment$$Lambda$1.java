package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.android.core.responses.UserCommunityCommitmentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CommunityCommitmentCancelAccountFragment$$Lambda$1 implements Action1 {
    private final CommunityCommitmentCancelAccountFragment arg$1;

    private CommunityCommitmentCancelAccountFragment$$Lambda$1(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment) {
        this.arg$1 = communityCommitmentCancelAccountFragment;
    }

    public static Action1 lambdaFactory$(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment) {
        return new CommunityCommitmentCancelAccountFragment$$Lambda$1(communityCommitmentCancelAccountFragment);
    }

    public void call(Object obj) {
        CommunityCommitmentCancelAccountFragment.lambda$new$0(this.arg$1, (UserCommunityCommitmentResponse) obj);
    }
}
