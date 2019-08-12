package com.airbnb.android.booking.fragments;

import p032rx.functions.Action0;

final /* synthetic */ class BookingSummaryFragment$$Lambda$23 implements Action0 {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$23(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Action0 lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$23(bookingSummaryFragment);
    }

    public void call() {
        this.arg$1.onAlipayRedirectError();
    }
}
