package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.activities.SpecialOfferActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SpecialOfferActivity$$Icepick<T extends SpecialOfferActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9478H = new Helper("com.airbnb.android.lib.activities.SpecialOfferActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.provider = (TripInformationProvider) f9478H.getParcelable(state, "provider");
            target.startDate = (AirDate) f9478H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f9478H.getParcelable(state, "endDate");
            target.numGuests = f9478H.getInt(state, "numGuests");
            target.maxGuests = f9478H.getInt(state, "maxGuests");
            target.initialPrice = f9478H.getInt(state, "initialPrice");
            target.selectedListing = (Listing) f9478H.getParcelable(state, "selectedListing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9478H.putParcelable(state, "provider", target.provider);
        f9478H.putParcelable(state, "startDate", target.startDate);
        f9478H.putParcelable(state, "endDate", target.endDate);
        f9478H.putInt(state, "numGuests", target.numGuests);
        f9478H.putInt(state, "maxGuests", target.maxGuests);
        f9478H.putInt(state, "initialPrice", target.initialPrice);
        f9478H.putParcelable(state, "selectedListing", target.selectedListing);
    }
}
