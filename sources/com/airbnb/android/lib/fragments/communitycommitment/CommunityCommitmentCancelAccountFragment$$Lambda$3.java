package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CommunityCommitmentCancelAccountFragment$$Lambda$3 implements OnClickListener {
    private final CommunityCommitmentCancelAccountFragment arg$1;

    private CommunityCommitmentCancelAccountFragment$$Lambda$3(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment) {
        this.arg$1 = communityCommitmentCancelAccountFragment;
    }

    public static OnClickListener lambdaFactory$(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment) {
        return new CommunityCommitmentCancelAccountFragment$$Lambda$3(communityCommitmentCancelAccountFragment);
    }

    public void onClick(View view) {
        CommunityCommitmentCancelAccountFragment.lambda$onCreateView$2(this.arg$1, view);
    }
}
