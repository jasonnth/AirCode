package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Intent;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CommunityCommitmentCancelAccountFragment$$Lambda$5 implements LinkOnClickListener {
    private final CommunityCommitmentCancelAccountFragment arg$1;
    private final Intent arg$2;

    private CommunityCommitmentCancelAccountFragment$$Lambda$5(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, Intent intent) {
        this.arg$1 = communityCommitmentCancelAccountFragment;
        this.arg$2 = intent;
    }

    public static LinkOnClickListener lambdaFactory$(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, Intent intent) {
        return new CommunityCommitmentCancelAccountFragment$$Lambda$5(communityCommitmentCancelAccountFragment, intent);
    }

    public void onClickLink(int i) {
        CommunityCommitmentCancelAccountFragment.lambda$setupGetMoreHelpInfoRow$4(this.arg$1, this.arg$2, i);
    }
}
