package com.airbnb.android.wishlists;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.map.ExploreMapView;

public class WishListDetailsMapFragment_ViewBinding implements Unbinder {
    private WishListDetailsMapFragment target;

    public WishListDetailsMapFragment_ViewBinding(WishListDetailsMapFragment target2, View source) {
        this.target = target2;
        target2.mapView = (ExploreMapView) Utils.findRequiredViewAsType(source, C1758R.C1760id.map_view, "field 'mapView'", ExploreMapView.class);
    }

    public void unbind() {
        WishListDetailsMapFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mapView = null;
    }
}
