package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CommunityCommitmentLearnMoreFragment$$Lambda$1 implements OnClickListener {
    private final CommunityCommitmentLearnMoreFragment arg$1;

    private CommunityCommitmentLearnMoreFragment$$Lambda$1(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment) {
        this.arg$1 = communityCommitmentLearnMoreFragment;
    }

    public static OnClickListener lambdaFactory$(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment) {
        return new CommunityCommitmentLearnMoreFragment$$Lambda$1(communityCommitmentLearnMoreFragment);
    }

    public void onClick(View view) {
        CommunityCommitmentLearnMoreFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
