package com.airbnb.android.core.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment;
import com.airbnb.android.core.models.Reservation;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSCancellationPolicyFragment$$Icepick<T extends DLSCancellationPolicyFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8448H = new Helper("com.airbnb.android.core.fragments.DLSCancellationPolicyFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f8448H.getParcelable(state, "reservation");
            target.cancellationPolicy = f8448H.getString(state, "cancellationPolicy");
            target.cancellationPolicyType = f8448H.getString(state, "cancellationPolicyType");
            target.cancellationPolicyText = f8448H.getString(state, "cancellationPolicyText");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8448H.putParcelable(state, "reservation", target.reservation);
        f8448H.putString(state, "cancellationPolicy", target.cancellationPolicy);
        f8448H.putString(state, "cancellationPolicyType", target.cancellationPolicyType);
        f8448H.putString(state, "cancellationPolicyText", target.cancellationPolicyText);
    }
}
