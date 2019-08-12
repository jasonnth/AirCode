package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.StaticMapView;

/* renamed from: com.airbnb.n2.components.MapInterstitial_ViewBinding */
public class MapInterstitial_ViewBinding implements Unbinder {
    private MapInterstitial target;

    public MapInterstitial_ViewBinding(MapInterstitial target2, View source) {
        this.target = target2;
        target2.mapView = (StaticMapView) Utils.findRequiredViewAsType(source, R.id.static_map, "field 'mapView'", StaticMapView.class);
        target2.textContainer = Utils.findRequiredView(source, R.id.map_interstitial_text_container, "field 'textContainer'");
        target2.title = (TextView) Utils.findRequiredViewAsType(source, R.id.map_interstitial_title, "field 'title'", TextView.class);
        target2.subtitle = (TextView) Utils.findRequiredViewAsType(source, R.id.map_interstitial_subtitle, "field 'subtitle'", TextView.class);
    }

    public void unbind() {
        MapInterstitial target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mapView = null;
        target2.textContainer = null;
        target2.title = null;
        target2.subtitle = null;
    }
}
