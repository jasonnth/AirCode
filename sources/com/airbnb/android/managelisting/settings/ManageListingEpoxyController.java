package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public abstract class ManageListingEpoxyController extends AirEpoxyController implements UpdateListener, AirRecyclerViewAttachable {
    ManageListingDataController controller;

    public void attachToAirRecyclerView(AirRecyclerView arv) {
        arv.setEpoxyControllerAndBuildModels(this);
    }

    ManageListingEpoxyController(ManageListingDataController controller2) {
        this.controller = controller2;
    }
}
