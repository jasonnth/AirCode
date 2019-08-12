package com.airbnb.android.core.fragments;

final /* synthetic */ class ProgressDialogFragment$$Lambda$2 implements Runnable {
    private final ProgressDialogFragment arg$1;

    private ProgressDialogFragment$$Lambda$2(ProgressDialogFragment progressDialogFragment) {
        this.arg$1 = progressDialogFragment;
    }

    public static Runnable lambdaFactory$(ProgressDialogFragment progressDialogFragment) {
        return new ProgressDialogFragment$$Lambda$2(progressDialogFragment);
    }

    public void run() {
        this.arg$1.sendOnProgressComplete(true);
    }
}
