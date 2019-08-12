package com.airbnb.android.booking.controller;

import android.os.Bundle;
import com.airbnb.android.booking.activities.CreditCardActivity.Flow;
import com.airbnb.android.booking.controller.CreditCardNavigationController;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreditCardNavigationController$$Icepick<T extends CreditCardNavigationController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7425H = new Helper("com.airbnb.android.booking.controller.CreditCardNavigationController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.flow = (Flow) f7425H.getSerializable(state, ManageListingAnalytics.FLOW);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7425H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
    }
}
