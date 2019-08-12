package com.airbnb.android.insights.fragments.details;

import com.airbnb.p027n2.utils.SnackbarWrapper;

final /* synthetic */ class InsightsNightlyPriceFragment$1$$Lambda$1 implements Runnable {
    private final C65721 arg$1;
    private final SnackbarWrapper arg$2;

    private InsightsNightlyPriceFragment$1$$Lambda$1(C65721 r1, SnackbarWrapper snackbarWrapper) {
        this.arg$1 = r1;
        this.arg$2 = snackbarWrapper;
    }

    public static Runnable lambdaFactory$(C65721 r1, SnackbarWrapper snackbarWrapper) {
        return new InsightsNightlyPriceFragment$1$$Lambda$1(r1, snackbarWrapper);
    }

    public void run() {
        C65721.lambda$showUpdateAppSnackbar$1(this.arg$1, this.arg$2);
    }
}
