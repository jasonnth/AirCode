package com.airbnb.android.managelisting.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ManageListingBaseFragment$$Lambda$2 implements OnClickListener {
    private final ManageListingBaseFragment arg$1;

    private ManageListingBaseFragment$$Lambda$2(ManageListingBaseFragment manageListingBaseFragment) {
        this.arg$1 = manageListingBaseFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingBaseFragment manageListingBaseFragment) {
        return new ManageListingBaseFragment$$Lambda$2(manageListingBaseFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.onUnsavedChangesDiscarded();
    }
}
