package com.airbnb.android.lib.paidamenities.activities;

import android.os.Bundle;
import com.airbnb.android.lib.paidamenities.activities.HostAmenityActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostAmenityActivity$$Icepick<T extends HostAmenityActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9605H = new Helper("com.airbnb.android.lib.paidamenities.activities.HostAmenityActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingId = f9605H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9605H.putLong(state, "listingId", target.listingId);
    }
}
