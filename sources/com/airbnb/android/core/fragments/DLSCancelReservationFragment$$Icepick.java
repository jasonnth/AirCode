package com.airbnb.android.core.fragments;

import android.os.Bundle;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.core.fragments.DLSCancelReservationFragment;
import com.airbnb.android.core.models.Reservation;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSCancelReservationFragment$$Icepick<T extends DLSCancelReservationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8447H = new Helper("com.airbnb.android.core.fragments.DLSCancelReservationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f8447H.getParcelable(state, "reservation");
            target.cancellationData = (CancellationData) f8447H.getParcelable(state, "cancellationData");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8447H.putParcelable(state, "reservation", target.reservation);
        f8447H.putParcelable(state, "cancellationData", target.cancellationData);
    }
}
