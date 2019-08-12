package com.airbnb.android.core.map;

import android.util.LongSparseArray;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.views.AirbnbMapView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExploreWebMapMarkerManager extends ExploreMapMarkerManager {
    private static final long NONE = -1;
    private AirbnbMapView mapView;
    private final LongSparseArray<AirMapMarker<Mappable>> markerableIdToMarkerMap = new LongSparseArray<>();
    private final List<ExploreMapMarkerable> markerables = new ArrayList();
    private long selectedMarkerableId = -1;

    public void setup(AirbnbMapView mapView2) {
        this.mapView = mapView2;
    }

    public void teardown() {
        clearMarkers();
        this.mapView = null;
    }

    public void addMarker(ExploreMapMarkerable markerable) {
        addMarker(markerable, false);
    }

    public void addMarker(ExploreMapMarkerable markerable, boolean selected) {
        if (this.markerableIdToMarkerMap.indexOfKey(markerable.getId()) < 0) {
            this.markerables.add(markerable);
            AirMapMarker<Mappable> airMapMarker = markerable.buildMarker(selected, false);
            if (this.mapView.addMarker(airMapMarker)) {
                this.markerableIdToMarkerMap.put(markerable.getId(), airMapMarker);
                return;
            }
            return;
        }
        replaceMarker(markerable);
    }

    private void replaceMarker(ExploreMapMarkerable markerable) {
        boolean isSelected;
        if (removeMarkerableById(markerable.getId())) {
            this.markerables.add(markerable);
            this.mapView.removeMarker((AirMapMarker) this.markerableIdToMarkerMap.get(markerable.getId()));
            if (markerable.getId() == this.selectedMarkerableId) {
                isSelected = true;
            } else {
                isSelected = false;
            }
            AirMapMarker<Mappable> newMarker = markerable.buildMarker(isSelected, false);
            if (this.mapView.addMarker(newMarker)) {
                this.markerableIdToMarkerMap.put(markerable.getId(), newMarker);
            } else {
                this.markerableIdToMarkerMap.remove(markerable.getId());
            }
        }
    }

    public void removeMarker(long markerableId) {
        if (markerableId == this.selectedMarkerableId) {
            this.selectedMarkerableId = -1;
        }
        this.mapView.removeMarker((AirMapMarker) this.markerableIdToMarkerMap.get(markerableId));
        removeMarkerableById(markerableId);
        this.markerableIdToMarkerMap.remove(markerableId);
    }

    public void selectMarker(long markerableId) {
        this.mapView.highlightMarker(this.selectedMarkerableId, markerableId);
        this.selectedMarkerableId = markerableId;
    }

    public void clearMarkers() {
        this.markerables.clear();
        this.markerableIdToMarkerMap.clear();
    }

    public List<ExploreMapMarkerable> getMarkerables() {
        return new ArrayList(this.markerables);
    }

    private boolean removeMarkerableById(long markerableId) {
        Iterator<ExploreMapMarkerable> iterator = this.markerables.iterator();
        while (iterator.hasNext()) {
            if (markerableId == ((ExploreMapMarkerable) iterator.next()).getId()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
