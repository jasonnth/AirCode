package com.airbnb.android.explore.controllers;

import android.location.Location;
import android.os.Bundle;
import com.airbnb.android.core.models.ExplorePromotion;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.PaginationMetadata;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.ObservableRequestManager;
import com.airbnb.android.explore.data.ExploreFilters;

public interface ExploreDataRepository {
    boolean areTabsLoading();

    void cancelExploreTabsRequest();

    void cancelMetadataRequest();

    void fetchExplorePlaylist(long j);

    void fetchExploreTabs(TopLevelSearchParams topLevelSearchParams, ExploreFilters exploreFilters, ExploreSeeAllInfo exploreSeeAllInfo, String str, boolean z, Location location, String str2);

    void fetchNextPageForTab(String str, PaginationMetadata paginationMetadata, TopLevelSearchParams topLevelSearchParams, ExploreFilters exploreFilters, ExploreSeeAllInfo exploreSeeAllInfo, String str2, String str3, boolean z, Location location, String str4);

    void fetchTabMetaData(String str, TopLevelSearchParams topLevelSearchParams, SearchFilters searchFilters, ExperienceFilters experienceFilters, PlaceFilters placeFilters, ExploreSeeAllInfo exploreSeeAllInfo, String str2, String str3, String str4);

    void init(ObservableRequestManager observableRequestManager, Bundle bundle, ExploreDataRepositoryCallback exploreDataRepositoryCallback);

    boolean isFetchingTabMetaData();

    boolean isTabSectionLoading(String str);

    void onSaveInstanceState(Bundle bundle);

    void updateOptInNotification(ExplorePromotion explorePromotion);
}
