package com.airbnb.android.react.maps;

import com.airbnb.android.lib.contentproviders.PlacesSearchSuggestionProvider;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class RegionChangeEvent extends Event<RegionChangeEvent> {
    private final LatLngBounds bounds;
    private final LatLng center;
    private final boolean continuous;

    public RegionChangeEvent(int id, LatLngBounds bounds2, LatLng center2, boolean continuous2) {
        super(id);
        this.bounds = bounds2;
        this.center = center2;
        this.continuous = continuous2;
    }

    public String getEventName() {
        return "topChange";
    }

    public boolean canCoalesce() {
        return false;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap event = new WritableNativeMap();
        event.putBoolean("continuous", this.continuous);
        WritableMap region = new WritableNativeMap();
        region.putDouble("latitude", this.center.latitude);
        region.putDouble("longitude", this.center.longitude);
        region.putDouble("latitudeDelta", this.bounds.northeast.latitude - this.bounds.southwest.latitude);
        region.putDouble("longitudeDelta", this.bounds.northeast.longitude - this.bounds.southwest.longitude);
        event.putMap(PlacesSearchSuggestionProvider.DISPLAY_REGION, region);
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), event);
    }
}
