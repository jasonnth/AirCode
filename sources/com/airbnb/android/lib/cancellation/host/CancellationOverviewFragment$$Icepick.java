package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.lib.cancellation.host.CancellationOverviewFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CancellationOverviewFragment$$Icepick<T extends CancellationOverviewFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9506H = new Helper("com.airbnb.android.lib.cancellation.host.CancellationOverviewFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9506H.getParcelable(state, "reservation");
            target.reason = (ReservationCancellationReason) f9506H.getSerializable(state, CancellationAnalytics.VALUE_PAGE_REASON);
            target.message = f9506H.getString(state, "message");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9506H.putParcelable(state, "reservation", target.reservation);
        f9506H.putSerializable(state, CancellationAnalytics.VALUE_PAGE_REASON, target.reason);
        f9506H.putString(state, "message", target.message);
    }
}
