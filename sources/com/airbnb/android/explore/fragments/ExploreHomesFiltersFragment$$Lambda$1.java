package com.airbnb.android.explore.fragments;

final /* synthetic */ class ExploreHomesFiltersFragment$$Lambda$1 implements Runnable {
    private final ExploreHomesFiltersFragment arg$1;

    private ExploreHomesFiltersFragment$$Lambda$1(ExploreHomesFiltersFragment exploreHomesFiltersFragment) {
        this.arg$1 = exploreHomesFiltersFragment;
    }

    public static Runnable lambdaFactory$(ExploreHomesFiltersFragment exploreHomesFiltersFragment) {
        return new ExploreHomesFiltersFragment$$Lambda$1(exploreHomesFiltersFragment);
    }

    public void run() {
        ExploreHomesFiltersFragment.lambda$postUpdateSearchButtonAndFilters$0(this.arg$1);
    }
}
