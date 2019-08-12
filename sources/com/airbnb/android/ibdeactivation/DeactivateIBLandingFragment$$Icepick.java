package com.airbnb.android.ibdeactivation;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.ibdeactivation.DeactivateIBLandingFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DeactivateIBLandingFragment$$Icepick<T extends DeactivateIBLandingFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9048H = new Helper("com.airbnb.android.ibdeactivation.DeactivateIBLandingFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9048H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9048H.putParcelable(state, "listing", target.listing);
    }
}
