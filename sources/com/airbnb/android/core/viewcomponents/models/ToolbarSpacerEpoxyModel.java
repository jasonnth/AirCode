package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ToolbarSpacerEpoxyModel extends AirEpoxyModel<View> {
    int backgroundRes;

    public void bind(View view) {
        super.bind(view);
        view.setBackgroundResource(this.backgroundRes);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
