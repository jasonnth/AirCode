package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationInputGroupFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class LYSCityRegistrationInputGroupFragment$$Icepick<T extends LYSCityRegistrationInputGroupFragment> extends LYSBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9932H = new Helper("com.airbnb.android.listyourspacedls.fragments.LYSCityRegistrationInputGroupFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.inputGroup = (ListingRegistrationProcessInputGroup) f9932H.getParcelable(state, "inputGroup");
            target.nextGroupIndex = f9932H.getInt(state, "nextGroupIndex");
            target.listingAddress = (AirAddress) f9932H.getParcelable(state, "listingAddress");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9932H.putParcelable(state, "inputGroup", target.inputGroup);
        f9932H.putInt(state, "nextGroupIndex", target.nextGroupIndex);
        f9932H.putParcelable(state, "listingAddress", target.listingAddress);
    }
}
