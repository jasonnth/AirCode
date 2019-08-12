package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class CohostingInviteFriendAdapter$$Lambda$2 implements StringTextWatcher {
    private final CohostingInviteFriendAdapter arg$1;

    private CohostingInviteFriendAdapter$$Lambda$2(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        this.arg$1 = cohostingInviteFriendAdapter;
    }

    public static StringTextWatcher lambdaFactory$(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        return new CohostingInviteFriendAdapter$$Lambda$2(cohostingInviteFriendAdapter);
    }

    public void textUpdated(String str) {
        this.arg$1.updateInviteButtonAvailability();
    }
}
