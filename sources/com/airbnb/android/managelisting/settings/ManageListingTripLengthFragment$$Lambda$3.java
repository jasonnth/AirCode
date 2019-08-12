package com.airbnb.android.managelisting.settings;

final /* synthetic */ class ManageListingTripLengthFragment$$Lambda$3 implements Runnable {
    private final ManageListingTripLengthFragment arg$1;

    private ManageListingTripLengthFragment$$Lambda$3(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        this.arg$1 = manageListingTripLengthFragment;
    }

    public static Runnable lambdaFactory$(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        return new ManageListingTripLengthFragment$$Lambda$3(manageListingTripLengthFragment);
    }

    public void run() {
        ManageListingTripLengthFragment.lambda$scrollToSeasonalRequirementsSection$1(this.arg$1);
    }
}
