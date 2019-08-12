package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingInstantBookUpsellFragment$$Lambda$4 implements OnClickListener {
    private final ManageListingInstantBookUpsellFragment arg$1;

    private ManageListingInstantBookUpsellFragment$$Lambda$4(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment) {
        this.arg$1 = manageListingInstantBookUpsellFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment) {
        return new ManageListingInstantBookUpsellFragment$$Lambda$4(manageListingInstantBookUpsellFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.popToHome();
    }
}
