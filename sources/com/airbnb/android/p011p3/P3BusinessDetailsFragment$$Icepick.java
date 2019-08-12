package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.core.models.CommercialHostInfo;
import com.airbnb.android.p011p3.P3BusinessDetailsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3BusinessDetailsFragment$$Icepick */
public class P3BusinessDetailsFragment$$Icepick<T extends P3BusinessDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10534H = new Helper("com.airbnb.android.p3.P3BusinessDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hostId = f10534H.getLong(state, "hostId");
            target.commercialHostInfo = (CommercialHostInfo) f10534H.getParcelable(state, "commercialHostInfo");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10534H.putLong(state, "hostId", target.hostId);
        f10534H.putParcelable(state, "commercialHostInfo", target.commercialHostInfo);
    }
}
