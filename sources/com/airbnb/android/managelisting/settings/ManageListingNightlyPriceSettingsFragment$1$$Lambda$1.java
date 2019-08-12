package com.airbnb.android.managelisting.settings;

final /* synthetic */ class ManageListingNightlyPriceSettingsFragment$1$$Lambda$1 implements Runnable {
    private final C74351 arg$1;

    private ManageListingNightlyPriceSettingsFragment$1$$Lambda$1(C74351 r1) {
        this.arg$1 = r1;
    }

    public static Runnable lambdaFactory$(C74351 r1) {
        return new ManageListingNightlyPriceSettingsFragment$1$$Lambda$1(r1);
    }

    public void run() {
        C74351.lambda$showUpdateAppSnackbar$1(this.arg$1);
    }
}
