package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.p027n2.collections.AirRecyclerView;

public abstract class ManageListingAdapter extends AirEpoxyAdapter implements UpdateListener, AirRecyclerViewAttachable {
    ManageListingDataController controller;

    public void attachToAirRecyclerView(AirRecyclerView arv) {
        arv.setAdapter(this);
    }

    ManageListingAdapter(ManageListingDataController controller2) {
        super(true);
        this.controller = controller2;
    }
}
