package com.airbnb.android.lib.reservationresponse;

import android.os.Bundle;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.reservationresponse.AcceptReservationConfirmationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AcceptReservationConfirmationFragment$$Icepick<T extends AcceptReservationConfirmationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9656H = new Helper("com.airbnb.android.lib.reservationresponse.AcceptReservationConfirmationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.provider = (TripInformationProvider) f9656H.getParcelable(state, "provider");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9656H.putParcelable(state, "provider", target.provider);
    }
}
