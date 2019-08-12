package com.airbnb.android.core.models.find;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.find.SearchFilters;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SearchFilters$$Icepick<T extends SearchFilters> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8478H = new Helper("com.airbnb.android.core.models.find.SearchFilters$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.modifiedAt = (AirDateTime) f8478H.getParcelable(state, "modifiedAt");
            target.isInstantBookOnly = f8478H.getBoxedBoolean(state, "isInstantBookOnly");
            target.isBusinessTravelReady = f8478H.getBoolean(state, "isBusinessTravelReady");
            target.minPrice = f8478H.getInt(state, "minPrice");
            target.maxPrice = f8478H.getInt(state, "maxPrice");
            target.priceFilterSelection = f8478H.getInt(state, "priceFilterSelection");
            target.numBeds = f8478H.getInt(state, "numBeds");
            target.numBedrooms = f8478H.getInt(state, "numBedrooms");
            target.numBathrooms = f8478H.getInt(state, "numBathrooms");
            target.currencyType = f8478H.getString(state, "currencyType");
            target.amenities = f8478H.getParcelableArrayList(state, "amenities");
            target.amenitiesToFilterOut = f8478H.getParcelableArrayList(state, "amenitiesToFilterOut");
            target.roomTypes = f8478H.getParcelableArrayList(state, "roomTypes");
            target.propertyTypes = f8478H.getParcelableArrayList(state, "propertyTypes");
            target.tripPurpose = (TripPurpose) f8478H.getParcelable(state, "tripPurpose");
            target.keywords = f8478H.getStringArrayList(state, "keywords");
            target.languages = f8478H.getIntegerArrayList(state, "languages");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8478H.putParcelable(state, "modifiedAt", target.modifiedAt);
        f8478H.putBoxedBoolean(state, "isInstantBookOnly", target.isInstantBookOnly);
        f8478H.putBoolean(state, "isBusinessTravelReady", target.isBusinessTravelReady);
        f8478H.putInt(state, "minPrice", target.minPrice);
        f8478H.putInt(state, "maxPrice", target.maxPrice);
        f8478H.putInt(state, "priceFilterSelection", target.priceFilterSelection);
        f8478H.putInt(state, "numBeds", target.numBeds);
        f8478H.putInt(state, "numBedrooms", target.numBedrooms);
        f8478H.putInt(state, "numBathrooms", target.numBathrooms);
        f8478H.putString(state, "currencyType", target.currencyType);
        f8478H.putParcelableArrayList(state, "amenities", target.amenities);
        f8478H.putParcelableArrayList(state, "amenitiesToFilterOut", target.amenitiesToFilterOut);
        f8478H.putParcelableArrayList(state, "roomTypes", target.roomTypes);
        f8478H.putParcelableArrayList(state, "propertyTypes", target.propertyTypes);
        f8478H.putParcelable(state, "tripPurpose", target.tripPurpose);
        f8478H.putStringArrayList(state, "keywords", target.keywords);
        f8478H.putIntegerArrayList(state, "languages", target.languages);
    }
}
