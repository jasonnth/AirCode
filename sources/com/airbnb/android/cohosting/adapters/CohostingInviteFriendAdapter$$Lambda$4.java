package com.airbnb.android.cohosting.adapters;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CohostingInviteFriendAdapter$$Lambda$4 implements OnCheckedChangeListener {
    private final CohostingInviteFriendAdapter arg$1;

    private CohostingInviteFriendAdapter$$Lambda$4(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        this.arg$1 = cohostingInviteFriendAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        return new CohostingInviteFriendAdapter$$Lambda$4(cohostingInviteFriendAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.displayWarningIfNeeded();
    }
}
