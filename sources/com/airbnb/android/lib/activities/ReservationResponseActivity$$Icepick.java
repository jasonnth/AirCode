package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationResponseActivity$$Icepick<T extends ReservationResponseActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9476H = new Helper("com.airbnb.android.lib.activities.ReservationResponseActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9476H.getParcelable(state, "reservation");
            target.threadId = f9476H.getBoxedLong(state, ThreadDataModel.THREADID);
            target.declineReason = (DeclineReason) f9476H.getSerializable(state, "declineReason");
            target.requestIsDeclined = f9476H.getBoolean(state, "requestIsDeclined");
            target.isUpdateRequestOut = f9476H.getBoolean(state, "isUpdateRequestOut");
            target.messageTypeToEdit = (MessageType) f9476H.getSerializable(state, "messageTypeToEdit");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9476H.putParcelable(state, "reservation", target.reservation);
        f9476H.putBoxedLong(state, ThreadDataModel.THREADID, target.threadId);
        f9476H.putSerializable(state, "declineReason", target.declineReason);
        f9476H.putBoolean(state, "requestIsDeclined", target.requestIsDeclined);
        f9476H.putBoolean(state, "isUpdateRequestOut", target.isUpdateRequestOut);
        f9476H.putSerializable(state, "messageTypeToEdit", target.messageTypeToEdit);
    }
}
