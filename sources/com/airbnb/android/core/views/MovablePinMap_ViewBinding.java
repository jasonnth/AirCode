package com.airbnb.android.core.views;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class MovablePinMap_ViewBinding implements Unbinder {
    private MovablePinMap target;

    public MovablePinMap_ViewBinding(MovablePinMap target2) {
        this(target2, target2);
    }

    public MovablePinMap_ViewBinding(MovablePinMap target2, View source) {
        this.target = target2;
        target2.airbnbMapView = (AirbnbMapView) Utils.findRequiredViewAsType(source, C0716R.C0718id.airbnb_map_view, "field 'airbnbMapView'", AirbnbMapView.class);
        target2.locationPin = (ImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.location_pin, "field 'locationPin'", ImageView.class);
        target2.locationPinShadow = (ImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.location_pin_shadow, "field 'locationPinShadow'", ImageView.class);
        target2.locationPinCircle = (ImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.location_pin_circle, "field 'locationPinCircle'", ImageView.class);
    }

    public void unbind() {
        MovablePinMap target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.airbnbMapView = null;
        target2.locationPin = null;
        target2.locationPinShadow = null;
        target2.locationPinCircle = null;
    }
}
