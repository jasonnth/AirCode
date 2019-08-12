package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CohostingInviteFriendEpoxyController$$Lambda$5 implements LinkOnClickListener {
    private final CohostingInviteFriendEpoxyController arg$1;

    private CohostingInviteFriendEpoxyController$$Lambda$5(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        this.arg$1 = cohostingInviteFriendEpoxyController;
    }

    public static LinkOnClickListener lambdaFactory$(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        return new CohostingInviteFriendEpoxyController$$Lambda$5(cohostingInviteFriendEpoxyController);
    }

    public void onClickLink(int i) {
        this.arg$1.listener.openTermsOfServiceLink();
    }
}
