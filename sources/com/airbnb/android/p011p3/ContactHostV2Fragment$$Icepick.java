package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.p011p3.ContactHostV2Fragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.ContactHostV2Fragment$$Icepick */
public class ContactHostV2Fragment$$Icepick<T extends ContactHostV2Fragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10531H = new Helper("com.airbnb.android.p3.ContactHostV2Fragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f10531H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10531H.putParcelable(state, "listing", target.listing);
    }
}
