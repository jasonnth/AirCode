package com.airbnb.android.core.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.listeners.InfoWindowCreator;
import com.airbnb.android.core.C0716R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class MapWindowAdapter implements InfoWindowCreator, InfoWindowAdapter {
    private final Context context;

    public MapWindowAdapter(Context context2) {
        this.context = context2;
    }

    public View getInfoWindow(Marker marker) {
        return LayoutInflater.from(this.context).inflate(C0716R.layout.no_info_window, null);
    }

    public View getInfoContents(Marker marker) {
        return null;
    }

    public View createInfoWindow(AirMapMarker<?> airMapMarker) {
        return null;
    }
}
