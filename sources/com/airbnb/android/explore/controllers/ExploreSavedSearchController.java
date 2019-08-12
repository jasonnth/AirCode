package com.airbnb.android.explore.controllers;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.explore.requests.ExploreUpdateSavedSearchRequest;
import icepick.State;

public class ExploreSavedSearchController {
    private final AirbnbAccountManager accountManager;
    private final ExploreDataController dataController;
    @State
    protected AirDateTime savedAt;
    @State
    protected String savedSearchId;
    @State
    protected String source;

    public ExploreSavedSearchController(ExploreDataController dataController2, AirbnbAccountManager accountManager2, Bundle savedState) {
        this.dataController = dataController2;
        this.accountManager = accountManager2;
        if (savedState != null) {
            IcepickWrapper.restoreInstanceState(this, savedState);
        } else {
            setDefaultSourceAndId();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void updateFrom(SavedSearch savedSearch) {
        this.savedSearchId = savedSearch.getSavedSearchId();
        this.source = savedSearch.getSource();
        this.savedAt = AirDateTime.now();
    }

    public void reset() {
        setDefaultSourceAndId();
    }

    private void setDefaultSourceAndId() {
        this.savedSearchId = String.valueOf(System.currentTimeMillis());
        this.source = "android";
    }

    public void saveToServerIfOutdated() {
        if (isSavedSearchOutdated() && this.dataController.hasSearchTerm()) {
            this.savedAt = AirDateTime.now();
            new ExploreUpdateSavedSearchRequest(this.accountManager.getCurrentUserId(), this.dataController.getTopLevelSearchParams(), this.dataController.getHomesSearchFilters(), this.savedSearchId, this.source, this.dataController.getPlaceId()).execute(NetworkUtil.singleFireExecutor());
        }
    }

    private boolean isSavedSearchOutdated() {
        return this.savedAt == null || this.savedAt.isBefore(getSearchModifiedAt());
    }

    private AirDateTime getSearchModifiedAt() {
        AirDateTime searchParamsModifiedAt = this.dataController.getSearchParamsModifiedAt();
        AirDateTime searchFiltersModifiedAt = this.dataController.getHomesSearchFilters().getModifiedAt();
        return searchParamsModifiedAt.isAfter(searchFiltersModifiedAt) ? searchParamsModifiedAt : searchFiltersModifiedAt;
    }
}
