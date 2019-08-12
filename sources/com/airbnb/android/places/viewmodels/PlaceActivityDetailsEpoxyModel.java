package com.airbnb.android.places.viewmodels;

import com.airbnb.android.core.models.PlaceActivityAttribute;
import com.airbnb.android.places.views.PlaceActivityDetailsView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class PlaceActivityDetailsEpoxyModel extends AirEpoxyModel<PlaceActivityDetailsView> {
    List<PlaceActivityAttribute> items;

    public void bind(PlaceActivityDetailsView view) {
        super.bind(view);
        view.setItems(this.items);
    }

    public int getDividerViewType() {
        return 0;
    }
}
