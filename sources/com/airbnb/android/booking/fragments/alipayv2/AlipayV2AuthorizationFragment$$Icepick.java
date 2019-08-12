package com.airbnb.android.booking.fragments.alipayv2;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.alipayv2.AlipayV2AuthorizationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AlipayV2AuthorizationFragment$$Icepick<T extends AlipayV2AuthorizationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7441H = new Helper("com.airbnb.android.booking.fragments.alipayv2.AlipayV2AuthorizationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.pollingState = f7441H.getInt(state, "pollingState");
            target.queryEventFired = f7441H.getBoolean(state, "queryEventFired");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7441H.putInt(state, "pollingState", target.pollingState);
        f7441H.putBoolean(state, "queryEventFired", target.queryEventFired);
    }
}
