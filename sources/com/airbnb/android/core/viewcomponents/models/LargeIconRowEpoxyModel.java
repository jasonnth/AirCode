package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.LargeIconRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LargeIconRowEpoxyModel extends AirEpoxyModel<LargeIconRow> {
    int drawableRes;

    public void bind(LargeIconRow view) {
        super.bind(view);
        view.setDrawable(this.drawableRes);
    }

    public int getDividerViewType() {
        return -1;
    }
}
