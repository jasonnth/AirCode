package com.airbnb.android.cohosting.adapters;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CohostingInviteFriendAdapter$$Lambda$5 implements LinkOnClickListener {
    private final CohostingInviteFriendAdapter arg$1;

    private CohostingInviteFriendAdapter$$Lambda$5(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        this.arg$1 = cohostingInviteFriendAdapter;
    }

    public static LinkOnClickListener lambdaFactory$(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        return new CohostingInviteFriendAdapter$$Lambda$5(cohostingInviteFriendAdapter);
    }

    public void onClickLink(int i) {
        this.arg$1.listener.openTermsOfServiceLink();
    }
}
