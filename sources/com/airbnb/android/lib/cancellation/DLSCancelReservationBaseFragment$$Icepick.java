package com.airbnb.android.lib.cancellation;

import android.os.Bundle;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.lib.cancellation.DLSCancelReservationBaseFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSCancelReservationBaseFragment$$Icepick<T extends DLSCancelReservationBaseFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9503H = new Helper("com.airbnb.android.lib.cancellation.DLSCancelReservationBaseFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cancellationData = (CancellationData) f9503H.getParcelable(state, "cancellationData");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9503H.putParcelable(state, "cancellationData", target.cancellationData);
    }
}
