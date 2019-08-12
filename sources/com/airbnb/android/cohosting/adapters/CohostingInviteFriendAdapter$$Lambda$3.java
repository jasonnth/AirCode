package com.airbnb.android.cohosting.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class CohostingInviteFriendAdapter$$Lambda$3 implements Listener {
    private final CohostingInviteFriendAdapter arg$1;

    private CohostingInviteFriendAdapter$$Lambda$3(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        this.arg$1 = cohostingInviteFriendAdapter;
    }

    public static Listener lambdaFactory$(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        return new CohostingInviteFriendAdapter$$Lambda$3(cohostingInviteFriendAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.displayWarningIfNeeded();
    }
}
