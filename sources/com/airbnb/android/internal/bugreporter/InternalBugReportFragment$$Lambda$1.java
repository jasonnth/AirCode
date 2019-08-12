package com.airbnb.android.internal.bugreporter;

final /* synthetic */ class InternalBugReportFragment$$Lambda$1 implements Listener {
    private final InternalBugReportFragment arg$1;

    private InternalBugReportFragment$$Lambda$1(InternalBugReportFragment internalBugReportFragment) {
        this.arg$1 = internalBugReportFragment;
    }

    public static Listener lambdaFactory$(InternalBugReportFragment internalBugReportFragment) {
        return new InternalBugReportFragment$$Lambda$1(internalBugReportFragment);
    }

    public void pickPhoto() {
        this.arg$1.pickPhoto();
    }
}
