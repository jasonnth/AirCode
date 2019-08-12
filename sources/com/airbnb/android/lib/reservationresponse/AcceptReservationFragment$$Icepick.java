package com.airbnb.android.lib.reservationresponse;

import android.os.Bundle;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.reservationresponse.AcceptReservationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AcceptReservationFragment$$Icepick<T extends AcceptReservationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9657H = new Helper("com.airbnb.android.lib.reservationresponse.AcceptReservationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.provider = (TripInformationProvider) f9657H.getParcelable(state, "provider");
            target.isRequestAccepted = f9657H.getBoolean(state, "isRequestAccepted");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9657H.putParcelable(state, "provider", target.provider);
        f9657H.putBoolean(state, "isRequestAccepted", target.isRequestAccepted);
    }
}
