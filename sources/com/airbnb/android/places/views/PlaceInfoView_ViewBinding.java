package com.airbnb.android.places.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.StaticMapView;

public class PlaceInfoView_ViewBinding implements Unbinder {
    private PlaceInfoView target;

    public PlaceInfoView_ViewBinding(PlaceInfoView target2) {
        this(target2, target2);
    }

    public PlaceInfoView_ViewBinding(PlaceInfoView target2, View source) {
        this.target = target2;
        target2.mapView = (StaticMapView) Utils.findRequiredViewAsType(source, C7627R.C7629id.static_map, "field 'mapView'", StaticMapView.class);
        target2.nameView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.name, "field 'nameView'", AirTextView.class);
        target2.addressView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.address, "field 'addressView'", AirTextView.class);
        target2.hoursView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.hours, "field 'hoursView'", AirTextView.class);
        target2.phoneNumberView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.phone_number, "field 'phoneNumberView'", AirTextView.class);
        target2.websiteView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.website, "field 'websiteView'", AirTextView.class);
        target2.hoursDivider = Utils.findRequiredView(source, C7627R.C7629id.hours_divider, "field 'hoursDivider'");
        target2.phoneNumberDivider = Utils.findRequiredView(source, C7627R.C7629id.phone_number_divider, "field 'phoneNumberDivider'");
        target2.websiteDivider = Utils.findRequiredView(source, C7627R.C7629id.website_divider, "field 'websiteDivider'");
        target2.addressRow = Utils.findRequiredView(source, C7627R.C7629id.address_row, "field 'addressRow'");
        target2.hoursRow = Utils.findRequiredView(source, C7627R.C7629id.hours_row, "field 'hoursRow'");
        target2.phoneNumberRow = Utils.findRequiredView(source, C7627R.C7629id.phone_number_row, "field 'phoneNumberRow'");
        target2.websiteRow = Utils.findRequiredView(source, C7627R.C7629id.website_row, "field 'websiteRow'");
    }

    public void unbind() {
        PlaceInfoView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mapView = null;
        target2.nameView = null;
        target2.addressView = null;
        target2.hoursView = null;
        target2.phoneNumberView = null;
        target2.websiteView = null;
        target2.hoursDivider = null;
        target2.phoneNumberDivider = null;
        target2.websiteDivider = null;
        target2.addressRow = null;
        target2.hoursRow = null;
        target2.phoneNumberRow = null;
        target2.websiteRow = null;
    }
}
