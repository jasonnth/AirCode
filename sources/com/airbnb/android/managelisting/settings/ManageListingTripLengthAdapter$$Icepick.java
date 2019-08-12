package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingTripLengthAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingTripLengthAdapter$$Icepick<T extends ManageListingTripLengthAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10190H = new Helper("com.airbnb.android.managelisting.settings.ManageListingTripLengthAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.seasonalRequirements = f10190H.getParcelableArrayList(state, "seasonalRequirements");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10190H.putParcelableArrayList(state, "seasonalRequirements", target.seasonalRequirements);
    }
}
