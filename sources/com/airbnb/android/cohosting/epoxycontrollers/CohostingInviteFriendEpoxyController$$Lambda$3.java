package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class CohostingInviteFriendEpoxyController$$Lambda$3 implements OnInputChangedListener {
    private final CohostingInviteFriendEpoxyController arg$1;

    private CohostingInviteFriendEpoxyController$$Lambda$3(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        this.arg$1 = cohostingInviteFriendEpoxyController;
    }

    public static OnInputChangedListener lambdaFactory$(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        return new CohostingInviteFriendEpoxyController$$Lambda$3(cohostingInviteFriendEpoxyController);
    }

    public void onInputChanged(String str) {
        this.arg$1.message = str;
    }
}
