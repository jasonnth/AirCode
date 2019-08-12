package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Intent;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CommunityCommitmentCancelAccountFragment$$Lambda$6 implements LinkOnClickListener {
    private final CommunityCommitmentCancelAccountFragment arg$1;
    private final Intent arg$2;

    private CommunityCommitmentCancelAccountFragment$$Lambda$6(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, Intent intent) {
        this.arg$1 = communityCommitmentCancelAccountFragment;
        this.arg$2 = intent;
    }

    public static LinkOnClickListener lambdaFactory$(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, Intent intent) {
        return new CommunityCommitmentCancelAccountFragment$$Lambda$6(communityCommitmentCancelAccountFragment, intent);
    }

    public void onClickLink(int i) {
        this.arg$1.startActivity(this.arg$2);
    }
}
