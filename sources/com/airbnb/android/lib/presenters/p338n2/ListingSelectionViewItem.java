package com.airbnb.android.lib.presenters.p338n2;

import android.content.Context;
import com.airbnb.android.core.models.Listing;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

/* renamed from: com.airbnb.android.lib.presenters.n2.ListingSelectionViewItem */
public class ListingSelectionViewItem implements SelectionViewItemPresenter {
    private final Listing listing;

    public ListingSelectionViewItem(Listing listing2) {
        this.listing = listing2;
    }

    public String getDisplayText(Context context) {
        return this.listing.getName();
    }

    public Listing getListing() {
        return this.listing;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ListingSelectionViewItem)) {
            return ((ListingSelectionViewItem) obj).getListing().equals(this.listing);
        }
        return false;
    }
}
