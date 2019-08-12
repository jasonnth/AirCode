package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.cancellation.host.HostCancellationParams;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.fragments.ReservationCanceledFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationCanceledFragment$$Icepick<T extends ReservationCanceledFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9549H = new Helper("com.airbnb.android.lib.fragments.ReservationCanceledFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9549H.getParcelable(state, "reservation");
            target.reservationCancellationInfo = (ReservationCancellationInfo) f9549H.getParcelable(state, "reservationCancellationInfo");
            target.cancellationParams = (HostCancellationParams) f9549H.getParcelable(state, "cancellationParams");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9549H.putParcelable(state, "reservation", target.reservation);
        f9549H.putParcelable(state, "reservationCancellationInfo", target.reservationCancellationInfo);
        f9549H.putParcelable(state, "cancellationParams", target.cancellationParams);
    }
}
