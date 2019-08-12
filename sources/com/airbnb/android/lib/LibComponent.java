package com.airbnb.android.lib;

import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.lib.activities.HomeActivity;
import com.airbnb.android.lib.activities.booking.BookingActivity;
import com.airbnb.android.lib.activities.booking.BookingV2Activity;
import com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment;

@ChildScope
public interface LibComponent {

    public interface Builder {
        LibComponent build();
    }

    void inject(HomeActivity homeActivity);

    void inject(BookingActivity bookingActivity);

    void inject(BookingV2Activity bookingV2Activity);

    void inject(PhoneVerificationFragment phoneVerificationFragment);
}
