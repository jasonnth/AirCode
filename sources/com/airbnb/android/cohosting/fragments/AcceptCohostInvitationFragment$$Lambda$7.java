package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class AcceptCohostInvitationFragment$$Lambda$7 implements LinkOnClickListener {
    private final AcceptCohostInvitationFragment arg$1;

    private AcceptCohostInvitationFragment$$Lambda$7(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        this.arg$1 = acceptCohostInvitationFragment;
    }

    public static LinkOnClickListener lambdaFactory$(AcceptCohostInvitationFragment acceptCohostInvitationFragment) {
        return new AcceptCohostInvitationFragment$$Lambda$7(acceptCohostInvitationFragment);
    }

    public void onClickLink(int i) {
        CohostingUtil.openTermsOfService(this.arg$1.getContext());
    }
}
