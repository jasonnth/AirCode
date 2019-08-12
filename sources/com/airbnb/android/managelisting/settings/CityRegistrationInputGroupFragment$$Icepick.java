package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.managelisting.settings.CityRegistrationInputGroupFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationInputGroupFragment$$Icepick<T extends CityRegistrationInputGroupFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10172H = new Helper("com.airbnb.android.managelisting.settings.CityRegistrationInputGroupFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.inputGroup = (ListingRegistrationProcessInputGroup) f10172H.getParcelable(state, "inputGroup");
            target.nextGroupIndex = f10172H.getInt(state, "nextGroupIndex");
            target.listingAddress = (AirAddress) f10172H.getParcelable(state, "listingAddress");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10172H.putParcelable(state, "inputGroup", target.inputGroup);
        f10172H.putInt(state, "nextGroupIndex", target.nextGroupIndex);
        f10172H.putParcelable(state, "listingAddress", target.listingAddress);
    }
}
