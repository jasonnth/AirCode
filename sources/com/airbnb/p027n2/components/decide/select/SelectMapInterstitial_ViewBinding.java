package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.StaticMapView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMapInterstitial_ViewBinding */
public class SelectMapInterstitial_ViewBinding implements Unbinder {
    private SelectMapInterstitial target;

    public SelectMapInterstitial_ViewBinding(SelectMapInterstitial target2, View source) {
        this.target = target2;
        target2.mapView = (StaticMapView) Utils.findRequiredViewAsType(source, R.id.static_map, "field 'mapView'", StaticMapView.class);
    }

    public void unbind() {
        SelectMapInterstitial target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mapView = null;
    }
}
