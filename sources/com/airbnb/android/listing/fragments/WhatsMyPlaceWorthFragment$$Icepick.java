package com.airbnb.android.listing.fragments;

import android.os.Bundle;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.WhatsMyPlaceWorth;
import com.airbnb.android.listing.fragments.WhatsMyPlaceWorthFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WhatsMyPlaceWorthFragment$$Icepick<T extends WhatsMyPlaceWorthFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9806H = new Helper("com.airbnb.android.listing.fragments.WhatsMyPlaceWorthFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loadingEstimate = f9806H.getBoolean(state, "loadingEstimate");
            target.address = (AirAddress) f9806H.getParcelable(state, "address");
            target.capacity = f9806H.getInt(state, "capacity");
            target.spaceType = (SpaceType) f9806H.getSerializable(state, "spaceType");
            target.estimatedValue = (WhatsMyPlaceWorth) f9806H.getParcelable(state, "estimatedValue");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9806H.putBoolean(state, "loadingEstimate", target.loadingEstimate);
        f9806H.putParcelable(state, "address", target.address);
        f9806H.putInt(state, "capacity", target.capacity);
        f9806H.putSerializable(state, "spaceType", target.spaceType);
        f9806H.putParcelable(state, "estimatedValue", target.estimatedValue);
    }
}
