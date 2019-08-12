package com.airbnb.android.booking.fragments;

import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class BookingReviewFragment$$Lambda$2 implements OnCheckedChangeListener {
    private final BookingReviewFragment arg$1;
    private final boolean arg$2;
    private final BookingController arg$3;

    private BookingReviewFragment$$Lambda$2(BookingReviewFragment bookingReviewFragment, boolean z, BookingController bookingController) {
        this.arg$1 = bookingReviewFragment;
        this.arg$2 = z;
        this.arg$3 = bookingController;
    }

    public static OnCheckedChangeListener lambdaFactory$(BookingReviewFragment bookingReviewFragment, boolean z, BookingController bookingController) {
        return new BookingReviewFragment$$Lambda$2(bookingReviewFragment, z, bookingController);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        BookingReviewFragment.lambda$setUpBusinessTripToggle$1(this.arg$1, this.arg$2, this.arg$3, switchRowInterface, z);
    }
}
