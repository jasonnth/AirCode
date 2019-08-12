package com.airbnb.android.core.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.WebViewActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WebViewActivity$$Icepick<T extends WebViewActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8422H = new Helper("com.airbnb.android.core.activities.WebViewActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasFinishedSessionRequest = f8422H.getBoolean(state, "hasFinishedSessionRequest");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8422H.putBoolean(state, "hasFinishedSessionRequest", target.hasFinishedSessionRequest);
    }
}
