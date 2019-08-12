package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.p011p3.AccountVerificationContactHostFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment$$Icepick */
public class AccountVerificationContactHostFragment$$Icepick<T extends AccountVerificationContactHostFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10530H = new Helper("com.airbnb.android.p3.AccountVerificationContactHostFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingId = f10530H.getLong(state, "listingId");
            target.hasBookings = f10530H.getBoolean(state, "hasBookings");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10530H.putLong(state, "listingId", target.listingId);
        f10530H.putBoolean(state, "hasBookings", target.hasBookings);
    }
}
