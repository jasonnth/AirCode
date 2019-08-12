package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$19 implements Listener {
    private final ManageListingCheckInGuideFragment arg$1;
    private final int arg$2;
    private final long arg$3;

    private ManageListingCheckInGuideFragment$$Lambda$19(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, int i, long j) {
        this.arg$1 = manageListingCheckInGuideFragment;
        this.arg$2 = i;
        this.arg$3 = j;
    }

    public static Listener lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, int i, long j) {
        return new ManageListingCheckInGuideFragment$$Lambda$19(manageListingCheckInGuideFragment, i, j);
    }

    public void itemSelected(Object obj) {
        ManageListingCheckInGuideFragment.lambda$showEditStepOptions$16(this.arg$1, this.arg$2, this.arg$3, (EditStepAction) obj);
    }
}
