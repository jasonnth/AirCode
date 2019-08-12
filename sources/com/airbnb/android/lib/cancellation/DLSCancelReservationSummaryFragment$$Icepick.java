package com.airbnb.android.lib.cancellation;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.cancellation.DLSCancelReservationSummaryFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class DLSCancelReservationSummaryFragment$$Icepick<T extends DLSCancelReservationSummaryFragment> extends DLSCancelReservationBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9504H = new Helper("com.airbnb.android.lib.cancellation.DLSCancelReservationSummaryFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9504H.getParcelable(state, "reservation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9504H.putParcelable(state, "reservation", target.reservation);
    }
}
