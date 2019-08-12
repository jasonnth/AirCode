package com.airbnb.android.booking.fragments;

final /* synthetic */ class SecurityCodeFragment$$Lambda$1 implements Runnable {
    private final SecurityCodeFragment arg$1;

    private SecurityCodeFragment$$Lambda$1(SecurityCodeFragment securityCodeFragment) {
        this.arg$1 = securityCodeFragment;
    }

    public static Runnable lambdaFactory$(SecurityCodeFragment securityCodeFragment) {
        return new SecurityCodeFragment$$Lambda$1(securityCodeFragment);
    }

    public void run() {
        this.arg$1.sheetInput.showSoftKeyboard();
    }
}
