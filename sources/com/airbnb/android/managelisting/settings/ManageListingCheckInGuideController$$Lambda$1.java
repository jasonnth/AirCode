package com.airbnb.android.managelisting.settings;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.p027n2.collections.Carousel;

final /* synthetic */ class ManageListingCheckInGuideController$$Lambda$1 implements OnModelBoundListener {
    private final ManageListingCheckInGuideController arg$1;

    private ManageListingCheckInGuideController$$Lambda$1(ManageListingCheckInGuideController manageListingCheckInGuideController) {
        this.arg$1 = manageListingCheckInGuideController;
    }

    public static OnModelBoundListener lambdaFactory$(ManageListingCheckInGuideController manageListingCheckInGuideController) {
        return new ManageListingCheckInGuideController$$Lambda$1(manageListingCheckInGuideController);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        ((Carousel) obj).scrollToPosition(this.arg$1.currentCarouselPosition);
    }
}
