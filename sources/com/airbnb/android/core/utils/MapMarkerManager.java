package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.utils.MapMarkerable;
import java.util.Set;

public abstract class MapMarkerManager<T extends MapMarkerable> {
    public abstract void addLocalAttractionToMap(AirMapView airMapView, LocalAttraction localAttraction);

    public abstract void addMarkerableToMap(AirMapView airMapView, T t);

    public abstract void clear();

    public abstract Set<AirMapMarker<?>> getAllMarkers();

    public abstract void highlightListingMarker(Context context, AirMapView airMapView, T t, T t2);

    public abstract void highlightLocalAttractionMarker(Context context, LocalAttraction localAttraction);
}
