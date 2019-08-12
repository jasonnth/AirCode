package com.airbnb.android.lib.cancellation;

import android.os.Bundle;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.cancellation.DLSCancelReservationActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSCancelReservationActivity$$Icepick<T extends DLSCancelReservationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9502H = new Helper("com.airbnb.android.lib.cancellation.DLSCancelReservationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cancellationData = (CancellationData) f9502H.getParcelable(state, "cancellationData");
            target.confirmationCode = f9502H.getString(state, "confirmationCode");
            target.reservation = (Reservation) f9502H.getParcelable(state, "reservation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9502H.putParcelable(state, "cancellationData", target.cancellationData);
        f9502H.putString(state, "confirmationCode", target.confirmationCode);
        f9502H.putParcelable(state, "reservation", target.reservation);
    }
}
