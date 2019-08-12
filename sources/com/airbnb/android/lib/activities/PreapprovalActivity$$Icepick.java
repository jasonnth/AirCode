package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.activities.PreapprovalActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PreapprovalActivity$$Icepick<T extends PreapprovalActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9472H = new Helper("com.airbnb.android.lib.activities.PreapprovalActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.provider = (TripInformationProvider) f9472H.getParcelable(state, "provider");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9472H.putParcelable(state, "provider", target.provider);
    }
}
