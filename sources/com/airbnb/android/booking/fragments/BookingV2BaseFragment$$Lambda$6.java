package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.BookingNavigationView;

final /* synthetic */ class BookingV2BaseFragment$$Lambda$6 implements OnClickListener {
    private final BookingV2BaseFragment arg$1;
    private final BookingNavigationView arg$2;

    private BookingV2BaseFragment$$Lambda$6(BookingV2BaseFragment bookingV2BaseFragment, BookingNavigationView bookingNavigationView) {
        this.arg$1 = bookingV2BaseFragment;
        this.arg$2 = bookingNavigationView;
    }

    public static OnClickListener lambdaFactory$(BookingV2BaseFragment bookingV2BaseFragment, BookingNavigationView bookingNavigationView) {
        return new BookingV2BaseFragment$$Lambda$6(bookingV2BaseFragment, bookingNavigationView);
    }

    public void onClick(View view) {
        BookingV2BaseFragment.lambda$setUpNavButton$6(this.arg$1, this.arg$2, view);
    }
}
