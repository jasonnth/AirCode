package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.ListSpacer;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ListSpacerEpoxyModel extends AirEpoxyModel<ListSpacer> {
    int spaceHeight;
    int spaceHeightRes;

    public void bind(ListSpacer listSpacer) {
        super.bind(listSpacer);
        if (this.spaceHeightRes != 0) {
            listSpacer.setSpaceHeightRes(this.spaceHeightRes);
        } else {
            listSpacer.setSpaceHeight(this.spaceHeight);
        }
    }
}
