package com.airbnb.epoxy;

import android.support.p000v4.widget.Space;
import com.airbnb.viewmodeladapter.R;

class HiddenEpoxyModel extends EpoxyModel<Space> {
    HiddenEpoxyModel() {
    }

    public int getDefaultLayout() {
        return R.layout.view_holder_empty_view;
    }

    public int getSpanSize(int spanCount, int position, int itemCount) {
        return 0;
    }
}
