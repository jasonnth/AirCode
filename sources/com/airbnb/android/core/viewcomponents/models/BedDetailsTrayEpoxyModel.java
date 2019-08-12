package com.airbnb.android.core.viewcomponents.models;

import android.text.TextUtils;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.views.BedDetailsTray;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class BedDetailsTrayEpoxyModel extends AirEpoxyModel<BedDetailsTray> {
    List<ListingRoom> rooms;
    CharSequence title;
    int titleRes;

    public void bind(BedDetailsTray view) {
        super.bind(view);
        CharSequence title2 = this.title;
        if (TextUtils.isEmpty(title2) && this.titleRes > 0) {
            title2 = view.getContext().getString(this.titleRes);
        }
        view.setup(title2, this.rooms);
    }

    public int getDividerViewType() {
        return 0;
    }
}
