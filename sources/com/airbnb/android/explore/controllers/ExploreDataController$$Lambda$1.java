package com.airbnb.android.explore.controllers;

import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;

final /* synthetic */ class ExploreDataController$$Lambda$1 implements Runnable {
    private final ExploreDataController arg$1;
    private final String arg$2;
    private final SearchFilters arg$3;
    private final ExperienceFilters arg$4;
    private final PlaceFilters arg$5;

    private ExploreDataController$$Lambda$1(ExploreDataController exploreDataController, String str, SearchFilters searchFilters, ExperienceFilters experienceFilters, PlaceFilters placeFilters) {
        this.arg$1 = exploreDataController;
        this.arg$2 = str;
        this.arg$3 = searchFilters;
        this.arg$4 = experienceFilters;
        this.arg$5 = placeFilters;
    }

    public static Runnable lambdaFactory$(ExploreDataController exploreDataController, String str, SearchFilters searchFilters, ExperienceFilters experienceFilters, PlaceFilters placeFilters) {
        return new ExploreDataController$$Lambda$1(exploreDataController, str, searchFilters, experienceFilters, placeFilters);
    }

    public void run() {
        this.arg$1.fetchTabMetaData(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
