package com.airbnb.android.explore.controllers;

import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.views.ExploreScrollController;

public interface ExploreControllerInterface {
    ExploreDataController getDataController();

    ExploreJitneyLogger getLogger();

    ExploreNavigationController getNavigationController();

    ExploreScrollController getScrollController();

    RecycledViewPool getViewPool();
}
