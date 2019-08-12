package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.WebViewActivity$$Icepick;
import com.airbnb.android.lib.activities.AirlockActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class AirlockActivity$$Icepick<T extends AirlockActivity> extends WebViewActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9461H = new Helper("com.airbnb.android.lib.activities.AirlockActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isLoginAirlock = f9461H.getBoolean(state, "isLoginAirlock");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9461H.putBoolean(state, "isLoginAirlock", target.isLoginAirlock);
    }
}
