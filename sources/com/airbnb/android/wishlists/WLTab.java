package com.airbnb.android.wishlists;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.WishList;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum WLTab {
    Homes(C1758R.string.wl_tab_homes_capitalized, C2139ExploreSubtab.Homes),
    Trips(C1758R.string.wl_tab_experiences_capitalized, C2139ExploreSubtab.Experiences),
    Places(C1758R.string.wl_tab_places_capitalized, C2139ExploreSubtab.Places),
    Stories(C1758R.string.wl_tab_stories_capitalized, C2139ExploreSubtab.Unknown);
    
    private final C2139ExploreSubtab forJitney;
    final int titleRes;

    private WLTab(int titleRes2, C2139ExploreSubtab forJitney2) {
        this.titleRes = titleRes2;
        this.forJitney = forJitney2;
    }

    static List<WLTab> getTabsForState(WishList wishList) {
        if (wishList == null) {
            return Collections.emptyList();
        }
        List<WLTab> tabs = new ArrayList<>(3);
        if (wishList.hasListings()) {
            tabs.add(Homes);
        }
        if (wishList.hasTrips()) {
            tabs.add(Trips);
        }
        if (wishList.hasPlaces() || wishList.hasPlaceActivities()) {
            tabs.add(Places);
        }
        if (!wishList.hasStoryArticles()) {
            return tabs;
        }
        tabs.add(Stories);
        return tabs;
    }

    public static C2139ExploreSubtab forJitney(WLTab tab) {
        if (tab != null) {
            return tab.forJitney;
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Tab is null"));
        return C2139ExploreSubtab.Homes;
    }
}
