package com.airbnb.android.managelisting.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ManageListingStatusSettingFragment$1$$Lambda$2 implements OnClickListener {
    private final C74501 arg$1;

    private ManageListingStatusSettingFragment$1$$Lambda$2(C74501 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C74501 r1) {
        return new ManageListingStatusSettingFragment$1$$Lambda$2(r1);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C74501.lambda$deactivateListing$1(this.arg$1, dialogInterface, i);
    }
}
