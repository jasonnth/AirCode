package com.airbnb.android.cohosting.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostingInviteFriendEpoxyController$$Lambda$1 implements OnClickListener {
    private final CohostingInviteFriendEpoxyController arg$1;

    private CohostingInviteFriendEpoxyController$$Lambda$1(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        this.arg$1 = cohostingInviteFriendEpoxyController;
    }

    public static OnClickListener lambdaFactory$(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        return new CohostingInviteFriendEpoxyController$$Lambda$1(cohostingInviteFriendEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.pickContact();
    }
}
