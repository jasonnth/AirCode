package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingCheckInGuideController$$Lambda$3 implements OnClickListener {
    private final ManageListingCheckInGuideController arg$1;
    private final int arg$2;
    private final long arg$3;

    private ManageListingCheckInGuideController$$Lambda$3(ManageListingCheckInGuideController manageListingCheckInGuideController, int i, long j) {
        this.arg$1 = manageListingCheckInGuideController;
        this.arg$2 = i;
        this.arg$3 = j;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckInGuideController manageListingCheckInGuideController, int i, long j) {
        return new ManageListingCheckInGuideController$$Lambda$3(manageListingCheckInGuideController, i, j);
    }

    public void onClick(View view) {
        this.arg$1.listener.addPhoto(this.arg$2, this.arg$3);
    }
}
