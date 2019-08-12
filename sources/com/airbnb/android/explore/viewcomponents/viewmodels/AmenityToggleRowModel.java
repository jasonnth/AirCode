package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Amenity;

public abstract class AmenityToggleRowModel extends ExploreInlineFiltersToggleRowEpoxyModel {
    Amenity amenity;

    public AmenityToggleRowModel amenity(Amenity amenity2) {
        mo11718id((CharSequence) amenity2.name());
        this.titleRes = amenity2.stringRes;
        return this;
    }
}
