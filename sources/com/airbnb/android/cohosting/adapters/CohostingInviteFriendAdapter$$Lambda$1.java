package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostingInviteFriendAdapter$$Lambda$1 implements OnClickListener {
    private final CohostingInviteFriendAdapter arg$1;

    private CohostingInviteFriendAdapter$$Lambda$1(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        this.arg$1 = cohostingInviteFriendAdapter;
    }

    public static OnClickListener lambdaFactory$(CohostingInviteFriendAdapter cohostingInviteFriendAdapter) {
        return new CohostingInviteFriendAdapter$$Lambda$1(cohostingInviteFriendAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.pickContact();
    }
}
