package com.airbnb.android.payout.manage.controllers;

import com.airbnb.android.core.responses.GetExistingPayoutMethodResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManagePayoutDataController$$Lambda$1 implements Action1 {
    private final ManagePayoutDataController arg$1;

    private ManagePayoutDataController$$Lambda$1(ManagePayoutDataController managePayoutDataController) {
        this.arg$1 = managePayoutDataController;
    }

    public static Action1 lambdaFactory$(ManagePayoutDataController managePayoutDataController) {
        return new ManagePayoutDataController$$Lambda$1(managePayoutDataController);
    }

    public void call(Object obj) {
        this.arg$1.onFetchExistingPayoutMethodSuccess((GetExistingPayoutMethodResponse) obj);
    }
}
