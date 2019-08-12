package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.booking.controller.BookingController;

final /* synthetic */ class KonaManageGuestProfilesFragment$$Lambda$2 implements OnClickListener {
    private final BookingController arg$1;

    private KonaManageGuestProfilesFragment$$Lambda$2(BookingController bookingController) {
        this.arg$1 = bookingController;
    }

    public static OnClickListener lambdaFactory$(BookingController bookingController) {
        return new KonaManageGuestProfilesFragment$$Lambda$2(bookingController);
    }

    public void onClick(View view) {
        this.arg$1.getBookingActivityFacade().showPriceDetails();
    }
}
