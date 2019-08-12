package com.airbnb.android.lib.presenters.p338n2;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.lib.presenters.n2.ListingSelectionView$$Lambda$1 */
final /* synthetic */ class ListingSelectionView$$Lambda$1 implements Function {
    private static final ListingSelectionView$$Lambda$1 instance = new ListingSelectionView$$Lambda$1();

    private ListingSelectionView$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new ListingSelectionViewItem((Listing) obj);
    }
}
