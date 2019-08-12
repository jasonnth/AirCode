package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.lib.activities.RequestTestSuiteActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class RequestTestSuiteActivity$$Icepick<T extends RequestTestSuiteActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9473H = new Helper("com.airbnb.android.lib.activities.RequestTestSuiteActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.logOutput = f9473H.getString(state, "logOutput");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9473H.putString(state, "logOutput", target.logOutput);
    }
}
