package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.cancellation.host.PenaltyFreeCancellationTrialFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PenaltyFreeCancellationTrialFragment$$Icepick<T extends PenaltyFreeCancellationTrialFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9512H = new Helper("com.airbnb.android.lib.cancellation.host.PenaltyFreeCancellationTrialFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9512H.getParcelable(state, "reservation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9512H.putParcelable(state, "reservation", target.reservation);
    }
}
