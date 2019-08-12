package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.booking.controller.BookingController;

final /* synthetic */ class KonaManageGuestProfilesFragment$$Lambda$3 implements OnClickListener {
    private final KonaManageGuestProfilesFragment arg$1;
    private final BookingController arg$2;

    private KonaManageGuestProfilesFragment$$Lambda$3(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment, BookingController bookingController) {
        this.arg$1 = konaManageGuestProfilesFragment;
        this.arg$2 = bookingController;
    }

    public static OnClickListener lambdaFactory$(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment, BookingController bookingController) {
        return new KonaManageGuestProfilesFragment$$Lambda$3(konaManageGuestProfilesFragment, bookingController);
    }

    public void onClick(View view) {
        KonaManageGuestProfilesFragment.lambda$setUpNavButton$3(this.arg$1, this.arg$2, view);
    }
}
