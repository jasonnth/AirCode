package com.airbnb.android.lib.payments.quickpay.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class HomesQuickPayFragment$$Icepick<T extends HomesQuickPayFragment> extends QuickPayFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9647H = new Helper("com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9647H.getParcelable(state, "reservation");
            target.couponCode = f9647H.getString(state, "couponCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9647H.putParcelable(state, "reservation", target.reservation);
        f9647H.putString(state, "couponCode", target.couponCode);
    }
}
