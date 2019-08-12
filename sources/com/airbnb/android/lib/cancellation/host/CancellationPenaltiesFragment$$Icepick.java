package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.cancellation.host.CancellationPenaltiesFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CancellationPenaltiesFragment$$Icepick<T extends CancellationPenaltiesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9507H = new Helper("com.airbnb.android.lib.cancellation.host.CancellationPenaltiesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9507H.getParcelable(state, "reservation");
            target.isModal = f9507H.getBoolean(state, "isModal");
            target.waivePenalties = f9507H.getBoolean(state, "waivePenalties");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9507H.putParcelable(state, "reservation", target.reservation);
        f9507H.putBoolean(state, "isModal", target.isModal);
        f9507H.putBoolean(state, "waivePenalties", target.waivePenalties);
    }
}
