package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BookingActivity$$Lambda$1 implements Action1 {
    private final BookingActivity arg$1;

    private BookingActivity$$Lambda$1(BookingActivity bookingActivity) {
        this.arg$1 = bookingActivity;
    }

    public static Action1 lambdaFactory$(BookingActivity bookingActivity) {
        return new BookingActivity$$Lambda$1(bookingActivity);
    }

    public void call(Object obj) {
        BookingActivity.lambda$new$0(this.arg$1, (ListingResponse) obj);
    }
}
