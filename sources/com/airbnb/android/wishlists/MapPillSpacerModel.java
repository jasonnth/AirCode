package com.airbnb.android.wishlists;

import android.view.View;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class MapPillSpacerModel extends AirEpoxyModel<View> {
    public int getDefaultLayout() {
        return C1758R.layout.view_model_map_pill_spacer;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
