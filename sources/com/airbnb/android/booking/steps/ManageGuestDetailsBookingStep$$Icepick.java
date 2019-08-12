package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.steps.ManageGuestDetailsBookingStep;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageGuestDetailsBookingStep$$Icepick<T extends ManageGuestDetailsBookingStep> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7444H = new Helper("com.airbnb.android.booking.steps.ManageGuestDetailsBookingStep$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.exclude = f7444H.getBoxedBoolean(state, "exclude");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7444H.putBoxedBoolean(state, "exclude", target.exclude);
    }
}
