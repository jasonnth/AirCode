package com.airbnb.android.explore;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.explore.adapters.BaseExploreAdapter;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment;
import com.airbnb.android.explore.fragments.MTExploreFragment;
import com.airbnb.android.explore.fragments.MTExploreParentFragment;
import com.airbnb.android.explore.fragments.MTHomesTabFragment;
import com.airbnb.android.explore.fragments.MTLocationChinaFragment;
import com.airbnb.android.explore.fragments.MTLocationFragment;
import com.airbnb.android.explore.fragments.MTTabFragment;
import com.airbnb.android.explore.views.ExplorePriceHistogramRow;

public interface ExploreGraph extends BaseGraph {
    void inject(BaseExploreAdapter baseExploreAdapter);

    void inject(ExploreDataController exploreDataController);

    void inject(ExploreHomesFiltersFragment exploreHomesFiltersFragment);

    void inject(MTExploreFragment mTExploreFragment);

    void inject(MTExploreParentFragment mTExploreParentFragment);

    void inject(MTHomesTabFragment mTHomesTabFragment);

    void inject(MTLocationChinaFragment mTLocationChinaFragment);

    void inject(MTLocationFragment mTLocationFragment);

    void inject(MTTabFragment mTTabFragment);

    void inject(ExplorePriceHistogramRow explorePriceHistogramRow);
}
