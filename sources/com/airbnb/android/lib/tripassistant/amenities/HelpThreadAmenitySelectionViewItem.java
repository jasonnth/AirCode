package com.airbnb.android.lib.tripassistant.amenities;

import android.content.Context;
import com.airbnb.android.core.models.HelpThreadAmenity;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

public class HelpThreadAmenitySelectionViewItem implements SelectionViewItemPresenter {
    private final HelpThreadAmenity amenity;

    public HelpThreadAmenitySelectionViewItem(HelpThreadAmenity amenity2) {
        this.amenity = amenity2;
    }

    public String getDisplayText(Context context) {
        return this.amenity.getName();
    }

    public HelpThreadAmenity getAmenity() {
        return this.amenity;
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof HelpThreadAmenitySelectionViewItem) && ((HelpThreadAmenitySelectionViewItem) o).amenity == this.amenity) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.amenity.hashCode();
    }
}
