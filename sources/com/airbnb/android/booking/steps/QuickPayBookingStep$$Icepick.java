package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.steps.QuickPayBookingStep;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class QuickPayBookingStep$$Icepick<T extends QuickPayBookingStep> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7445H = new Helper("com.airbnb.android.booking.steps.QuickPayBookingStep$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.showIdentity = f7445H.getBoolean(state, "showIdentity");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7445H.putBoolean(state, "showIdentity", target.showIdentity);
    }
}
