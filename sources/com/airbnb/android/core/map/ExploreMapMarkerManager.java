package com.airbnb.android.core.map;

import com.airbnb.android.core.views.AirbnbMapView;
import java.util.List;

public abstract class ExploreMapMarkerManager {
    public abstract void addMarker(ExploreMapMarkerable exploreMapMarkerable);

    public abstract void addMarker(ExploreMapMarkerable exploreMapMarkerable, boolean z);

    public abstract void clearMarkers();

    public abstract List<ExploreMapMarkerable> getMarkerables();

    public abstract void removeMarker(long j);

    public abstract void selectMarker(long j);

    public abstract void setup(AirbnbMapView airbnbMapView);

    public abstract void teardown();
}
