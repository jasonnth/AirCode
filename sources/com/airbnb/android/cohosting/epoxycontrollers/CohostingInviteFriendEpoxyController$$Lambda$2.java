package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class CohostingInviteFriendEpoxyController$$Lambda$2 implements StringTextWatcher {
    private final CohostingInviteFriendEpoxyController arg$1;

    private CohostingInviteFriendEpoxyController$$Lambda$2(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        this.arg$1 = cohostingInviteFriendEpoxyController;
    }

    public static StringTextWatcher lambdaFactory$(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        return new CohostingInviteFriendEpoxyController$$Lambda$2(cohostingInviteFriendEpoxyController);
    }

    public void textUpdated(String str) {
        this.arg$1.updateInviteButtonAvailability(str);
    }
}
