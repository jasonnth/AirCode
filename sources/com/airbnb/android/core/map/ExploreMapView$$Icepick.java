package com.airbnb.android.core.map;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.map.ExploreMapView;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.utils.MapState;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class ExploreMapView$$Icepick<T extends ExploreMapView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8465H = new Helper("com.airbnb.android.core.map.ExploreMapView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.currentlyHighlightedItem = (Mappable) f8465H.getParcelable(state, "currentlyHighlightedItem");
        target.currentMapState = (MapState) f8465H.getParcelable(state, "currentMapState");
        target.firstMapLoad = f8465H.getBoolean(state, "firstMapLoad");
        target.isInQuietMode = f8465H.getBoolean(state, "isInQuietMode");
        return super.restore(target, f8465H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8465H.putParent(super.save(target, p));
        f8465H.putParcelable(state, "currentlyHighlightedItem", target.currentlyHighlightedItem);
        f8465H.putParcelable(state, "currentMapState", target.currentMapState);
        f8465H.putBoolean(state, "firstMapLoad", target.firstMapLoad);
        f8465H.putBoolean(state, "isInQuietMode", target.isInQuietMode);
        return state;
    }
}
