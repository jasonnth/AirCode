package com.airbnb.android.explore.controllers;

import android.location.Location;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.ExploreMetaData;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.p008mt.data.ExploreData;
import com.airbnb.android.explore.controllers.ExploreDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExploreDataController$$Icepick<T extends ExploreDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8572H = new Helper("com.airbnb.android.explore.controllers.ExploreDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.data = (ExploreData) f8572H.getParcelable(state, "data");
            target.topLevelSearchParams = (TopLevelSearchParams) f8572H.getParcelable(state, "topLevelSearchParams");
            target.seeAllTopLevelSearchParams = (TopLevelSearchParams) f8572H.getParcelable(state, "seeAllTopLevelSearchParams");
            target.metadataBeforeSeeAllMode = (ExploreMetaData) f8572H.getParcelable(state, "metadataBeforeSeeAllMode");
            target.searchParamsModifiedAt = (AirDateTime) f8572H.getParcelable(state, "searchParamsModifiedAt");
            target.intentSource = f8572H.getString(state, "intentSource");
            target.userLocation = (Location) f8572H.getParcelable(state, "userLocation");
            target.inMapMode = f8572H.getBoolean(state, "inMapMode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8572H.putParcelable(state, "data", target.data);
        f8572H.putParcelable(state, "topLevelSearchParams", target.topLevelSearchParams);
        f8572H.putParcelable(state, "seeAllTopLevelSearchParams", target.seeAllTopLevelSearchParams);
        f8572H.putParcelable(state, "metadataBeforeSeeAllMode", target.metadataBeforeSeeAllMode);
        f8572H.putParcelable(state, "searchParamsModifiedAt", target.searchParamsModifiedAt);
        f8572H.putString(state, "intentSource", target.intentSource);
        f8572H.putParcelable(state, "userLocation", target.userLocation);
        f8572H.putBoolean(state, "inMapMode", target.inMapMode);
    }
}
