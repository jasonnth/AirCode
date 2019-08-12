package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.ArrivalDetailsFragment;
import com.airbnb.android.core.models.ArrivalDetails;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ArrivalDetailsFragment$$Icepick<T extends ArrivalDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7426H = new Helper("com.airbnb.android.booking.fragments.ArrivalDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkInTimePhrase = f7426H.getString(state, "checkInTimePhrase");
            target.selectedCheckIn = f7426H.getString(state, "selectedCheckIn");
            target.arrivalDetails = (ArrivalDetails) f7426H.getParcelable(state, "arrivalDetails");
            target.hostName = f7426H.getString(state, "hostName");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7426H.putString(state, "checkInTimePhrase", target.checkInTimePhrase);
        f7426H.putString(state, "selectedCheckIn", target.selectedCheckIn);
        f7426H.putParcelable(state, "arrivalDetails", target.arrivalDetails);
        f7426H.putString(state, "hostName", target.hostName);
    }
}
