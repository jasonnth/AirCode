package com.airbnb.android.cohosting.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ConfirmCohostInvitationEpoxyController$$Lambda$1 implements OnClickListener {
    private final ConfirmCohostInvitationEpoxyController arg$1;

    private ConfirmCohostInvitationEpoxyController$$Lambda$1(ConfirmCohostInvitationEpoxyController confirmCohostInvitationEpoxyController) {
        this.arg$1 = confirmCohostInvitationEpoxyController;
    }

    public static OnClickListener lambdaFactory$(ConfirmCohostInvitationEpoxyController confirmCohostInvitationEpoxyController) {
        return new ConfirmCohostInvitationEpoxyController$$Lambda$1(confirmCohostInvitationEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.transitionToManageListingPickerPage();
    }
}
