package com.airbnb.android.core.models.find;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.TimeType;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class TopLevelSearchParams$$Icepick<T extends TopLevelSearchParams> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8479H = new Helper("com.airbnb.android.core.models.find.TopLevelSearchParams$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.searchTerm = f8479H.getString(state, "searchTerm");
            target.checkInDate = (AirDate) f8479H.getParcelable(state, "checkInDate");
            target.checkOutDate = (AirDate) f8479H.getParcelable(state, "checkOutDate");
            target.guestDetails = (GuestDetails) f8479H.getParcelable(state, "guestDetails");
            target.mapBounds = (MapBounds) f8479H.getParcelable(state, "mapBounds");
            target.timeType = (TimeType) f8479H.getParcelable(state, "timeType");
            target.autocompletePlaceId = f8479H.getString(state, "autocompletePlaceId");
            target.searchInputType = (C5809SearchInputType) f8479H.getSerializable(state, "searchInputType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8479H.putString(state, "searchTerm", target.searchTerm);
        f8479H.putParcelable(state, "checkInDate", target.checkInDate);
        f8479H.putParcelable(state, "checkOutDate", target.checkOutDate);
        f8479H.putParcelable(state, "guestDetails", target.guestDetails);
        f8479H.putParcelable(state, "mapBounds", target.mapBounds);
        f8479H.putParcelable(state, "timeType", target.timeType);
        f8479H.putString(state, "autocompletePlaceId", target.autocompletePlaceId);
        f8479H.putSerializable(state, "searchInputType", target.searchInputType);
    }
}
