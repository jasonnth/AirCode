package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.beta.models.guidebook.GuidebookItem;
import com.airbnb.p027n2.components.PlaceCard;

public class GuidebookItemEpoxyModel extends GuidebookPlaceEpoxyModel_ {
    private GuidebookItem guidebookItem;

    public GuidebookItemEpoxyModel guidebookItem(GuidebookItem guidebookItem2) {
        this.guidebookItem = guidebookItem2;
        return this;
    }

    public void bind(PlaceCard view) {
        boldText(this.guidebookItem.getBoldSubtitle());
        regularText(this.guidebookItem.getNonBoldSubtitle());
        photo(this.guidebookItem.getCardPhoto());
        view.setCardDetails(this.guidebookItem.getLocalizedNameForExploreCard(), this.guidebookItem.getLocalizedTypeForExplore());
        super.bind(view);
    }
}
