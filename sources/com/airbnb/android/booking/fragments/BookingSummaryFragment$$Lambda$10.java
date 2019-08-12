package com.airbnb.android.booking.fragments;

final /* synthetic */ class BookingSummaryFragment$$Lambda$10 implements Runnable {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$10(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Runnable lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$10(bookingSummaryFragment);
    }

    public void run() {
        this.arg$1.scrollView.scrollTo(0, this.arg$1.confirmAndPayPrimaryButton.getBottom());
    }
}
