package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.utils.listing.BedDetailsDisplay;
import com.airbnb.p027n2.components.BedDetailsCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class BedDetailsCardEpoxyModel extends AirEpoxyModel<BedDetailsCard> {
    ListingRoom room;

    public void bind(BedDetailsCard view) {
        super.bind(view);
        view.setTitle((CharSequence) BedDetailsDisplay.getRoomName(view.getContext(), this.room));
        view.setIcons(BedDetailsDisplay.getIconsText(view.getContext(), this.room, true));
        view.setDescription(BedDetailsDisplay.getRoomDescription(view.getContext(), this.room, true));
    }
}
