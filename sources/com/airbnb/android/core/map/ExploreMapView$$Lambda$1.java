package com.airbnb.android.core.map;

import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import java.util.List;

final /* synthetic */ class ExploreMapView$$Lambda$1 implements WishListsChangedListener {
    private final ExploreMapView arg$1;

    private ExploreMapView$$Lambda$1(ExploreMapView exploreMapView) {
        this.arg$1 = exploreMapView;
    }

    public static WishListsChangedListener lambdaFactory$(ExploreMapView exploreMapView) {
        return new ExploreMapView$$Lambda$1(exploreMapView);
    }

    public void onWishListsChanged(List list, WishListChangeInfo wishListChangeInfo) {
        ExploreMapView.lambda$new$7(this.arg$1, list, wishListChangeInfo);
    }
}
