package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.cohosting.epoxycontrollers.ConfirmCohostInvitationEpoxyController.Listener;

final /* synthetic */ class ConfirmInvitationAcceptedFragment$$Lambda$1 implements Listener {
    private final ConfirmInvitationAcceptedFragment arg$1;

    private ConfirmInvitationAcceptedFragment$$Lambda$1(ConfirmInvitationAcceptedFragment confirmInvitationAcceptedFragment) {
        this.arg$1 = confirmInvitationAcceptedFragment;
    }

    public static Listener lambdaFactory$(ConfirmInvitationAcceptedFragment confirmInvitationAcceptedFragment) {
        return new ConfirmInvitationAcceptedFragment$$Lambda$1(confirmInvitationAcceptedFragment);
    }

    public void transitionToManageListingPickerPage() {
        this.arg$1.transitionToManageListingPickerPage();
    }
}
