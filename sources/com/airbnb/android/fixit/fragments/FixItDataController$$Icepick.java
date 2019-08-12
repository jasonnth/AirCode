package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.FixItReport;
import com.airbnb.android.fixit.fragments.FixItDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;
import p005cn.jpush.android.JPushConstants.PushService;

public class FixItDataController$$Icepick<T extends FixItDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8699H = new Helper("com.airbnb.android.fixit.fragments.FixItDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.report = (FixItReport) f8699H.getParcelable(state, PushService.PARAM_REPORT);
            target.isLoading = f8699H.getBoolean(state, "isLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8699H.putParcelable(state, PushService.PARAM_REPORT, target.report);
        f8699H.putBoolean(state, "isLoading", target.isLoading);
    }
}
