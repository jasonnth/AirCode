package com.airbnb.android.misnap;

final /* synthetic */ class MiSnapIdentityCaptureActivity$$Lambda$1 implements Runnable {
    private final MiSnapIdentityCaptureActivity arg$1;

    private MiSnapIdentityCaptureActivity$$Lambda$1(MiSnapIdentityCaptureActivity miSnapIdentityCaptureActivity) {
        this.arg$1 = miSnapIdentityCaptureActivity;
    }

    public static Runnable lambdaFactory$(MiSnapIdentityCaptureActivity miSnapIdentityCaptureActivity) {
        return new MiSnapIdentityCaptureActivity$$Lambda$1(miSnapIdentityCaptureActivity);
    }

    public void run() {
        this.arg$1.hideError();
    }
}
