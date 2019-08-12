package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import com.airbnb.android.lib.postbooking.PostBookingLandingFragment;
import com.airbnb.android.lib.postbooking.PostBookingLandingFragment.State;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PostBookingLandingFragment$$Icepick<T extends PostBookingLandingFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9653H = new Helper("com.airbnb.android.lib.postbooking.PostBookingLandingFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentState = (State) f9653H.getSerializable(state, "currentState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9653H.putSerializable(state, "currentState", target.currentState);
    }
}
