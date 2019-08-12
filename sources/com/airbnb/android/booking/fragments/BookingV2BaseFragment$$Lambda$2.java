package com.airbnb.android.booking.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BookingV2BaseFragment$$Lambda$2 implements Action1 {
    private final BookingV2BaseFragment arg$1;

    private BookingV2BaseFragment$$Lambda$2(BookingV2BaseFragment bookingV2BaseFragment) {
        this.arg$1 = bookingV2BaseFragment;
    }

    public static Action1 lambdaFactory$(BookingV2BaseFragment bookingV2BaseFragment) {
        return new BookingV2BaseFragment$$Lambda$2(bookingV2BaseFragment);
    }

    public void call(Object obj) {
        BookingV2BaseFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
