package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.android.core.responses.UserCommunityCommitmentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CommunityCommitmentFragment$$Lambda$1 implements Action1 {
    private final CommunityCommitmentFragment arg$1;

    private CommunityCommitmentFragment$$Lambda$1(CommunityCommitmentFragment communityCommitmentFragment) {
        this.arg$1 = communityCommitmentFragment;
    }

    public static Action1 lambdaFactory$(CommunityCommitmentFragment communityCommitmentFragment) {
        return new CommunityCommitmentFragment$$Lambda$1(communityCommitmentFragment);
    }

    public void call(Object obj) {
        CommunityCommitmentFragment.lambda$new$0(this.arg$1, (UserCommunityCommitmentResponse) obj);
    }
}
