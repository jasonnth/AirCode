package com.airbnb.android.lib.coldstart.graph;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.wishlists.WishListManager;

public interface AirActivityPreloadGraph extends BaseGraph {
    AirbnbApi airbnbApi();

    SuperHeroManager superHeroManager();

    WishListManager wishListManager();
}
