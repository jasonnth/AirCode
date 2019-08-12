package com.airbnb.android.places.fragments;

import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;

public class PlaceActivityMapFragment_ViewBinding implements Unbinder {
    private PlaceActivityMapFragment target;

    public PlaceActivityMapFragment_ViewBinding(PlaceActivityMapFragment target2, View source) {
        this.target = target2;
        target2.mapView = (AirbnbMapView) Utils.findRequiredViewAsType(source, C7627R.C7629id.map_view, "field 'mapView'", AirbnbMapView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7627R.C7629id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.placeDetailsRow = (StandardRow) Utils.findRequiredViewAsType(source, C7627R.C7629id.place_details, "field 'placeDetailsRow'", StandardRow.class);
        target2.actionableTextColor = ContextCompat.getColor(source.getContext(), C7627R.color.n2_text_color_actionable);
    }

    public void unbind() {
        PlaceActivityMapFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mapView = null;
        target2.toolbar = null;
        target2.placeDetailsRow = null;
    }
}
