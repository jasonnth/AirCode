package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.steps.GuestCountBookingStep;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class GuestCountBookingStep$$Icepick<T extends GuestCountBookingStep> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7442H = new Helper("com.airbnb.android.booking.steps.GuestCountBookingStep$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.exclude = f7442H.getBoxedBoolean(state, "exclude");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7442H.putBoxedBoolean(state, "exclude", target.exclude);
    }
}
