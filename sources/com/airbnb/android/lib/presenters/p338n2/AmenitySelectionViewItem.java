package com.airbnb.android.lib.presenters.p338n2;

import android.content.Context;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

/* renamed from: com.airbnb.android.lib.presenters.n2.AmenitySelectionViewItem */
public class AmenitySelectionViewItem implements SelectionViewItemPresenter {
    private final Amenity amenity;

    public AmenitySelectionViewItem(Amenity amenity2) {
        this.amenity = amenity2;
    }

    public String getDisplayText(Context context) {
        return context.getString(this.amenity.stringRes);
    }

    public Amenity getAmenity() {
        return this.amenity;
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof AmenitySelectionViewItem) && ((AmenitySelectionViewItem) o).amenity == this.amenity) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.amenity.f8471id;
    }
}
