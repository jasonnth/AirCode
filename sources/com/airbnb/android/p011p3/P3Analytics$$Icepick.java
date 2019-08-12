package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.p011p3.P3Analytics;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3Analytics$$Icepick */
public class P3Analytics$$Icepick<T extends P3Analytics> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10533H = new Helper("com.airbnb.android.p3.P3Analytics$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasLoggedImpression = f10533H.getBoolean(state, "hasLoggedImpression");
            target.impressionId = f10533H.getString(state, "impressionId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10533H.putBoolean(state, "hasLoggedImpression", target.hasLoggedImpression);
        f10533H.putString(state, "impressionId", target.impressionId);
    }
}
