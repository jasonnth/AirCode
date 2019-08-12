package com.airbnb.android.core.utils;

import android.content.Context;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapMarker.Builder;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.utils.MapMarkerable;
import com.airbnb.android.core.views.AirbnbMapView;
import com.google.android.gms.maps.model.LatLng;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WebMapMarkerManager<T extends MapMarkerable> extends MapMarkerManager<T> {
    private final Map<Long, AirMapMarker<T>> mListingsMap = new ArrayMap();
    private final Map<Long, AirMapMarker<LocalAttraction>> mLocalAttractionsMap = new ArrayMap();

    public void addMarkerableToMap(AirMapView airMapView, T markerable) {
        if (!this.mListingsMap.containsKey(Long.valueOf(markerable.getId()))) {
            AirMapMarker<T> airMapMarker = new Builder().object(markerable).position(markerable.getListing().getAndroidLatLng()).mo19456id(markerable.getId()).build();
            if (airMapView.addMarker(airMapMarker)) {
                this.mListingsMap.put(Long.valueOf(markerable.getId()), airMapMarker);
            }
        }
    }

    public void addLocalAttractionToMap(AirMapView airMapView, LocalAttraction attraction) {
        if (!this.mLocalAttractionsMap.containsKey(Long.valueOf(attraction.getResourceId()))) {
            AirMapMarker<LocalAttraction> airMapMarker = new Builder().object(attraction).position(new LatLng(attraction.getLatitude(), attraction.getLongitude())).mo19456id(attraction.getResourceId()).build();
            if (airMapView.addMarker(airMapMarker)) {
                this.mLocalAttractionsMap.put(Long.valueOf(attraction.getResourceId()), airMapMarker);
            }
        }
    }

    public void highlightListingMarker(Context context, AirMapView airMapView, T prevMarkerable, T currMarkerable) {
        ((AirbnbMapView) airMapView).highlightMarker(prevMarkerable != null ? prevMarkerable.getId() : -1, currMarkerable.getId());
    }

    public void highlightLocalAttractionMarker(Context context, LocalAttraction attraction) {
    }

    public void clear() {
        this.mListingsMap.clear();
    }

    public Set<AirMapMarker<?>> getAllMarkers() {
        return new HashSet(this.mListingsMap.values());
    }
}
