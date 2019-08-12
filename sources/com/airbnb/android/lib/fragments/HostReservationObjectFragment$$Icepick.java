package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.fragments.HostReservationObjectFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostReservationObjectFragment$$Icepick<T extends HostReservationObjectFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9533H = new Helper("com.airbnb.android.lib.fragments.HostReservationObjectFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f9533H.getString(state, "confirmationCode");
            target.reservationId = f9533H.getLong(state, "reservationId");
            target.source = (ROLaunchSource) f9533H.getSerializable(state, "source");
            target.provider = (TripInformationProvider) f9533H.getParcelable(state, "provider");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9533H.putString(state, "confirmationCode", target.confirmationCode);
        f9533H.putLong(state, "reservationId", target.reservationId);
        f9533H.putSerializable(state, "source", target.source);
        f9533H.putParcelable(state, "provider", target.provider);
    }
}
