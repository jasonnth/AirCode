package com.airbnb.android.lib.presenters.p338n2;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.models.Listing;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.lib.presenters.n2.ListingSelectionView */
public class ListingSelectionView extends BaseSelectionView<ListingSelectionViewItem> {
    public ListingSelectionView(Context context) {
        super(context);
    }

    public ListingSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListingSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initialize(List<Listing> allItems) {
        setItems((List<T>) FluentIterable.from((Iterable<E>) allItems).transform(ListingSelectionView$$Lambda$1.lambdaFactory$()).toList());
    }

    public void setSelectedListing(Listing listing) {
        List<ListingSelectionViewItem> items = getItems();
        for (int i = 0; i < items.size(); i++) {
            ListingSelectionViewItem item = (ListingSelectionViewItem) items.get(i);
            if (listing.equals(item.getListing())) {
                super.setSelectedItem(item);
            }
        }
    }

    public Listing getSelectedListing() {
        return ((ListingSelectionViewItem) getSelectedItem()).getListing();
    }
}
