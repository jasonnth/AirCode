package com.airbnb.android.explore.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.map.ExploreMapView;
import com.airbnb.android.explore.C0857R;

public class MTExploreMapFragment_ViewBinding implements Unbinder {
    private MTExploreMapFragment target;

    public MTExploreMapFragment_ViewBinding(MTExploreMapFragment target2, View source) {
        this.target = target2;
        target2.exploreMapView = (ExploreMapView) Utils.findRequiredViewAsType(source, C0857R.C0859id.map_view, "field 'exploreMapView'", ExploreMapView.class);
    }

    public void unbind() {
        MTExploreMapFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.exploreMapView = null;
    }
}
