package com.airbnb.android.ibdeactivation;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.ibdeactivation.DeactivateIBActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DeactivateIBActivity$$Icepick<T extends DeactivateIBActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9047H = new Helper("com.airbnb.android.ibdeactivation.DeactivateIBActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9047H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9047H.putParcelable(state, "listing", target.listing);
    }
}
