package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.fragments.DLSReservationObjectFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSReservationObjectFragment$$Icepick<T extends DLSReservationObjectFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9528H = new Helper("com.airbnb.android.lib.fragments.DLSReservationObjectFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isWifiBroadcastReceiverRegistered = f9528H.getBoolean(state, "isWifiBroadcastReceiverRegistered");
            target.informationProvider = (TripInformationProvider) f9528H.getParcelable(state, "informationProvider");
            target.hasLocalAttractions = f9528H.getBoxedBoolean(state, "hasLocalAttractions");
            target.isLoading = f9528H.getBoolean(state, "isLoading");
            target.helpThread = (HelpThread) f9528H.getParcelable(state, "helpThread");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9528H.putBoolean(state, "isWifiBroadcastReceiverRegistered", target.isWifiBroadcastReceiverRegistered);
        f9528H.putParcelable(state, "informationProvider", target.informationProvider);
        f9528H.putBoxedBoolean(state, "hasLocalAttractions", target.hasLocalAttractions);
        f9528H.putBoolean(state, "isLoading", target.isLoading);
        f9528H.putParcelable(state, "helpThread", target.helpThread);
    }
}
