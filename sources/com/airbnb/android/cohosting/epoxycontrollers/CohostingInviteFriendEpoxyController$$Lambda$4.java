package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.cohosting.utils.CohostingLoggingUtil;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;

final /* synthetic */ class CohostingInviteFriendEpoxyController$$Lambda$4 implements OnModelBoundListener {
    private final CohostingInviteFriendEpoxyController arg$1;

    private CohostingInviteFriendEpoxyController$$Lambda$4(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        this.arg$1 = cohostingInviteFriendEpoxyController;
    }

    public static OnModelBoundListener lambdaFactory$(CohostingInviteFriendEpoxyController cohostingInviteFriendEpoxyController) {
        return new CohostingInviteFriendEpoxyController$$Lambda$4(cohostingInviteFriendEpoxyController);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        this.arg$1.logger.logContractExistAlertImpression(CohostingLoggingUtil.getCohostingContext(this.arg$1.listing, this.arg$1.listingManagers), null);
    }
}
