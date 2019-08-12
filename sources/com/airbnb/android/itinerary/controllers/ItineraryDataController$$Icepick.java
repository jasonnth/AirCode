package com.airbnb.android.itinerary.controllers;

import android.os.Bundle;
import com.airbnb.android.itinerary.controllers.ItineraryDataController;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ItineraryDataController$$Icepick<T extends ItineraryDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8323H = new Helper("com.airbnb.android.itinerary.controllers.ItineraryDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.timelineTripPaginationOffset = f8323H.getInt(state, "timelineTripPaginationOffset");
            target.freeTimeItem = (FreeTimeItem) f8323H.getParcelable(state, "freeTimeItem");
            target.currentConfirmationCode = f8323H.getString(state, "currentConfirmationCode");
            target.reservationObjectId = f8323H.getString(state, "reservationObjectId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8323H.putInt(state, "timelineTripPaginationOffset", target.timelineTripPaginationOffset);
        f8323H.putParcelable(state, "freeTimeItem", target.freeTimeItem);
        f8323H.putString(state, "currentConfirmationCode", target.currentConfirmationCode);
        f8323H.putString(state, "reservationObjectId", target.reservationObjectId);
    }
}
