package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CommunityCommitmentFeedbackIntroFragment$$Lambda$1 implements OnClickListener {
    private final CommunityCommitmentFeedbackIntroFragment arg$1;

    private CommunityCommitmentFeedbackIntroFragment$$Lambda$1(CommunityCommitmentFeedbackIntroFragment communityCommitmentFeedbackIntroFragment) {
        this.arg$1 = communityCommitmentFeedbackIntroFragment;
    }

    public static OnClickListener lambdaFactory$(CommunityCommitmentFeedbackIntroFragment communityCommitmentFeedbackIntroFragment) {
        return new CommunityCommitmentFeedbackIntroFragment$$Lambda$1(communityCommitmentFeedbackIntroFragment);
    }

    public void onClick(View view) {
        this.arg$1.onBackPressed();
    }
}
