package com.airbnb.android.booking.fragments;

final /* synthetic */ class ExpirationDateFragment$$Lambda$1 implements Runnable {
    private final ExpirationDateFragment arg$1;

    private ExpirationDateFragment$$Lambda$1(ExpirationDateFragment expirationDateFragment) {
        this.arg$1 = expirationDateFragment;
    }

    public static Runnable lambdaFactory$(ExpirationDateFragment expirationDateFragment) {
        return new ExpirationDateFragment$$Lambda$1(expirationDateFragment);
    }

    public void run() {
        this.arg$1.sheetInput.showSoftKeyboard();
    }
}
