package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.cancellation.host.HostCancellationActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostCancellationActivity$$Icepick<T extends HostCancellationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9509H = new Helper("com.airbnb.android.lib.cancellation.host.HostCancellationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9509H.getParcelable(state, "reservation");
            target.confirmationCode = f9509H.getString(state, "confirmationCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9509H.putParcelable(state, "reservation", target.reservation);
        f9509H.putString(state, "confirmationCode", target.confirmationCode);
    }
}
