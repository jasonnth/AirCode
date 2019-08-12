package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.interfaces.OnHomeListener;

final /* synthetic */ class BookingV2Activity$$Lambda$1 implements OnHomeListener {
    private final BookingV2Activity arg$1;

    private BookingV2Activity$$Lambda$1(BookingV2Activity bookingV2Activity) {
        this.arg$1 = bookingV2Activity;
    }

    public static OnHomeListener lambdaFactory$(BookingV2Activity bookingV2Activity) {
        return new BookingV2Activity$$Lambda$1(bookingV2Activity);
    }

    public boolean onHomePressed() {
        return this.arg$1.onBackPressed();
    }
}
