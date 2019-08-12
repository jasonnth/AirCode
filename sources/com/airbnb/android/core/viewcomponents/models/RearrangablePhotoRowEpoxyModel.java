package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.photorearranger.RearrangablePhotoRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.imaging.Image;

public abstract class RearrangablePhotoRowEpoxyModel extends AirEpoxyModel<RearrangablePhotoRow> {
    Image image;
    int labelRes;

    public void bind(RearrangablePhotoRow view) {
        super.bind(view);
        view.setImage(this.image);
        view.setLabelRes(this.labelRes);
        view.setLabelVisible(this.labelRes != 0);
    }

    public int getDividerViewType() {
        return -1;
    }
}
