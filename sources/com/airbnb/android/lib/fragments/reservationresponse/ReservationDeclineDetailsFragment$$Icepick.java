package com.airbnb.android.lib.fragments.reservationresponse;

import android.os.Bundle;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineDetailsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationDeclineDetailsFragment$$Icepick<T extends ReservationDeclineDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9582H = new Helper("com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.declineReason = (DeclineReason) f9582H.getSerializable(state, "declineReason");
            target.messages = (HashMap) f9582H.getSerializable(state, "messages");
            target.isLoading = f9582H.getBoolean(state, "isLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9582H.putSerializable(state, "declineReason", target.declineReason);
        f9582H.putSerializable(state, "messages", target.messages);
        f9582H.putBoolean(state, "isLoading", target.isLoading);
    }
}
