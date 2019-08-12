package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.cancellation.host.HostCancellationParams;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.activities.ReservationCancellationActivity;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.InputReason;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationCancellationActivity$$Icepick<T extends ReservationCancellationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9475H = new Helper("com.airbnb.android.lib.activities.ReservationCancellationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9475H.getParcelable(state, "reservation");
            target.reservationCancellationInfo = (ReservationCancellationInfo) f9475H.getParcelable(state, "reservationCancellationInfo");
            target.inputReason = (InputReason) f9475H.getSerializable(state, "inputReason");
            target.cancellationReason = (ReservationCancellationReason) f9475H.getSerializable(state, "cancellationReason");
            target.cancellationParams = (HostCancellationParams) f9475H.getParcelable(state, "cancellationParams");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9475H.putParcelable(state, "reservation", target.reservation);
        f9475H.putParcelable(state, "reservationCancellationInfo", target.reservationCancellationInfo);
        f9475H.putSerializable(state, "inputReason", target.inputReason);
        f9475H.putSerializable(state, "cancellationReason", target.cancellationReason);
        f9475H.putParcelable(state, "cancellationParams", target.cancellationParams);
    }
}
