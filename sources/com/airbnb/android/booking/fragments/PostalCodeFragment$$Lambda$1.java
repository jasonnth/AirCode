package com.airbnb.android.booking.fragments;

final /* synthetic */ class PostalCodeFragment$$Lambda$1 implements Runnable {
    private final PostalCodeFragment arg$1;

    private PostalCodeFragment$$Lambda$1(PostalCodeFragment postalCodeFragment) {
        this.arg$1 = postalCodeFragment;
    }

    public static Runnable lambdaFactory$(PostalCodeFragment postalCodeFragment) {
        return new PostalCodeFragment$$Lambda$1(postalCodeFragment);
    }

    public void run() {
        this.arg$1.sheetInput.showSoftKeyboard();
    }
}
