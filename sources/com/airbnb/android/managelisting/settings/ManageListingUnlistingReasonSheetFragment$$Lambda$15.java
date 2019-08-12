package com.airbnb.android.managelisting.settings;

final /* synthetic */ class ManageListingUnlistingReasonSheetFragment$$Lambda$15 implements Runnable {
    private final ManageListingActionExecutor arg$1;

    private ManageListingUnlistingReasonSheetFragment$$Lambda$15(ManageListingActionExecutor manageListingActionExecutor) {
        this.arg$1 = manageListingActionExecutor;
    }

    public static Runnable lambdaFactory$(ManageListingActionExecutor manageListingActionExecutor) {
        return new ManageListingUnlistingReasonSheetFragment$$Lambda$15(manageListingActionExecutor);
    }

    public void run() {
        this.arg$1.nightlyPrice();
    }
}
