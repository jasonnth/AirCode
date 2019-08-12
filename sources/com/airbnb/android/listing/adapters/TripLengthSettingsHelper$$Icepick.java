package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.TripLengthSettingsHelper;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class TripLengthSettingsHelper$$Icepick<T extends TripLengthSettingsHelper> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9804H = new Helper("com.airbnb.android.listing.adapters.TripLengthSettingsHelper$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.minimumNights = f9804H.getBoxedInt(state, "minimumNights");
            target.maximumNights = f9804H.getBoxedInt(state, "maximumNights");
            target.weekendMinNights = f9804H.getBoxedInt(state, "weekendMinNights");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9804H.putBoxedInt(state, "minimumNights", target.minimumNights);
        f9804H.putBoxedInt(state, "maximumNights", target.maximumNights);
        f9804H.putBoxedInt(state, "weekendMinNights", target.weekendMinNights);
    }
}
