package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.requests.booking.responses.BookingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BookingSummaryFragment$$Lambda$6 implements Action1 {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$6(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Action1 lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$6(bookingSummaryFragment);
    }

    public void call(Object obj) {
        BookingSummaryFragment.lambda$new$17(this.arg$1, (BookingResponse) obj);
    }
}
