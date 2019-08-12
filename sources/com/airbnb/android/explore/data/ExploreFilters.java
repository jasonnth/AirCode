package com.airbnb.android.explore.data;

import android.os.Bundle;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;

public class ExploreFilters {
    private ExperienceFilters experienceSearchFilters;
    private SearchFilters homesSearchFilters;
    private PlaceFilters placesSearchFilters;
    private ExperienceFilters seeAllExperienceSearchFilters;
    private PlaceFilters seeAllPlacesSearchFilters;

    public ExploreFilters(Bundle savedState) {
        this.homesSearchFilters = new SearchFilters(savedState);
        this.placesSearchFilters = new PlaceFilters(savedState);
        this.experienceSearchFilters = new ExperienceFilters(savedState);
    }

    public ExperienceFilters getExperienceSearchFilters() {
        return this.seeAllExperienceSearchFilters != null ? this.seeAllExperienceSearchFilters : this.experienceSearchFilters;
    }

    public void setExperienceSearchFilters(ExperienceFilters newFilters, boolean isInSeeAllMode) {
        ExperienceFilters clonedFilters = ExperienceFilters.cloneFrom(newFilters, true);
        if (isInSeeAllMode) {
            this.seeAllExperienceSearchFilters = clonedFilters;
        } else {
            this.experienceSearchFilters = clonedFilters;
        }
    }

    public SearchFilters getHomesSearchFilters() {
        return this.homesSearchFilters;
    }

    public void setHomesSearchFilters(SearchFilters newFilters) {
        this.homesSearchFilters = SearchFilters.cloneFrom(newFilters, true);
    }

    public PlaceFilters getPlacesSearchFilters() {
        return this.seeAllPlacesSearchFilters != null ? this.seeAllPlacesSearchFilters : this.placesSearchFilters;
    }

    public void setPlacesSearchFilters(PlaceFilters newFilters, boolean isInSeeAllMode) {
        PlaceFilters clonedFilters = PlaceFilters.cloneFrom(newFilters, true);
        if (isInSeeAllMode) {
            this.seeAllPlacesSearchFilters = clonedFilters;
        } else {
            this.placesSearchFilters = clonedFilters;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        this.experienceSearchFilters.onSaveInstanceState(outState);
        this.homesSearchFilters.onSaveInstanceState(outState);
        this.placesSearchFilters.onSaveInstanceState(outState);
        if (this.seeAllExperienceSearchFilters != null) {
            this.seeAllExperienceSearchFilters.onSaveInstanceState(outState);
        }
        if (this.seeAllPlacesSearchFilters != null) {
            this.seeAllPlacesSearchFilters.onSaveInstanceState(outState);
        }
    }

    public void resetAllFilters() {
        this.homesSearchFilters.resetToDefaults();
        this.placesSearchFilters.reset();
        this.experienceSearchFilters.resetToDefaults();
    }

    public void exitSeeAllMode(String tabId, boolean persistSeeAllVerticalFilters) {
        if (persistSeeAllVerticalFilters) {
            if (Tab.EXPERIENCE.isSameAs(tabId) && this.seeAllExperienceSearchFilters != null) {
                this.experienceSearchFilters = ExperienceFilters.cloneFrom(this.seeAllExperienceSearchFilters, true);
            } else if (Tab.PLACES.isSameAs(tabId) && this.seeAllPlacesSearchFilters != null) {
                this.placesSearchFilters = PlaceFilters.cloneFrom(this.seeAllPlacesSearchFilters, true);
            }
        }
        this.seeAllExperienceSearchFilters = null;
        this.seeAllPlacesSearchFilters = null;
    }

    public int getDetailFiltersCount(String tabId) {
        if (Tab.EXPERIENCE.isSameAs(tabId)) {
            return getExperienceSearchFilters().getDetailFiltersCount();
        }
        if (Tab.HOME.isSameAs(tabId)) {
            return getHomesSearchFilters().getDetailFiltersCount();
        }
        if (Tab.PLACES.isSameAs(tabId)) {
            return getPlacesSearchFilters().getDetailFiltersCount();
        }
        return 0;
    }

    public void resetFiltersForTab(String tabId) {
        if (Tab.EXPERIENCE.isSameAs(tabId)) {
            getExperienceSearchFilters().resetToDefaults();
        } else if (Tab.HOME.isSameAs(tabId)) {
            getHomesSearchFilters().resetToDefaults();
        } else if (Tab.PLACES.isSameAs(tabId)) {
            getPlacesSearchFilters().reset();
        }
    }
}
