package com.airbnb.android.explore.fragments;

final /* synthetic */ class ExploreExperiencesFiltersFragment$$Lambda$1 implements Runnable {
    private final ExploreExperiencesFiltersFragment arg$1;

    private ExploreExperiencesFiltersFragment$$Lambda$1(ExploreExperiencesFiltersFragment exploreExperiencesFiltersFragment) {
        this.arg$1 = exploreExperiencesFiltersFragment;
    }

    public static Runnable lambdaFactory$(ExploreExperiencesFiltersFragment exploreExperiencesFiltersFragment) {
        return new ExploreExperiencesFiltersFragment$$Lambda$1(exploreExperiencesFiltersFragment);
    }

    public void run() {
        ExploreExperiencesFiltersFragment.lambda$postUpdateSearchButtonAndFilters$0(this.arg$1);
    }
}
