package com.airbnb.android.explore.controllers;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.ExperiencesMetaData;
import com.airbnb.android.core.models.ExploreMetaData;
import com.airbnb.android.core.models.ExplorePlaylist;
import com.airbnb.android.core.models.ExplorePromotion;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.FilterSuggestionFilters;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.core.models.PaginationMetadata;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SearchMetaData;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.models.SearchUrgencyCommitment;
import com.airbnb.android.core.models.SeeAllInfoQuery;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.MapBounds;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.p008mt.data.ExploreData;
import com.airbnb.android.core.p008mt.data.TabMap;
import com.airbnb.android.core.requests.ObservableRequestManager;
import com.airbnb.android.core.responses.ExplorePlaylistResponse;
import com.airbnb.android.core.responses.ExploreResponse;
import com.airbnb.android.core.responses.ExploreTabResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreNagivationInterface;
import com.airbnb.android.explore.data.ExploreFilters;
import com.airbnb.android.explore.requests.ExploreTabRequest;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.Lists;
import com.squareup.otto.Bus;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;

public class ExploreDataController implements ExploreDataRepositoryCallback {
    private static final int DEBOUNCE_DELAY_MS = 1000;
    private static final String TAG = ExploreDataController.class.getSimpleName();
    private static final float USER_LOCATION_HARD_REFRESH_THRESHOLD_IN_METERS = 150000.0f;
    private final Bus bus;
    private final BusinessTravelAccountManager businessTravelAccountManager;
    private final BusinessTravelJitneyLogger businessTravelJitneyLogger;
    private ExplorePlaylist currentPlaylist;
    @State
    ExploreData data = ExploreData.builder().build();
    private final Set<ExploreDataChangedListener> dataChangedListenerSet = new LinkedHashSet();
    private final ExploreDataRepository dataRepository;
    private final ExploreDataStore exploreDataStore;
    private final ExploreFilters filters;
    private final GPSPermissionGetter gpsPermissionGetter;
    private final Handler handler = new Handler();
    @State
    boolean inMapMode;
    @State
    String intentSource;
    @State
    ExploreMetaData metadataBeforeSeeAllMode;
    private final ExploreNagivationInterface nagivationInterface;
    private final ExplorePerformanceAnalytics perfAnalytics;
    private final Object refreshFiltersRunnableToken = new Object();
    private final ExploreSavedSearchController savedSearchController;
    @State
    AirDateTime searchParamsModifiedAt = AirDateTime.now();
    private TabMap seeAllTabs = new TabMap();
    @State
    TopLevelSearchParams seeAllTopLevelSearchParams;
    private String selectedVerticalFromSearch;
    private String tabIdFromDeeplink;
    private TabMap tabs = new TabMap();
    @State
    TopLevelSearchParams topLevelSearchParams = TopLevelSearchParams.builder().build();
    @State
    Location userLocation;
    private final WishListManager wishListManager;

    public interface ExploreDataChangedListener {
        void onExplorePlaylistLoaded();

        void onSearchParamsUpdated();

        void onTabContentUpdated(String str, boolean z, NetworkException networkException);

        void onTabMetadataUpdated(String str);

        void onTabsLoadError(NetworkException networkException);

        void onTabsLoaded(String str, boolean z);
    }

    public boolean hasMapBounds() {
        return this.topLevelSearchParams.hasMapBounds();
    }

    public ExploreDataController(Bundle savedState, ObservableRequestManager requestManager, AirbnbAccountManager accountManager, ExploreDataRepository dataRepository2, AirbnbPreferences airbnbPreferences, Bus bus2, WishListManager wishListManager2, BusinessTravelAccountManager businessTravelAccountManager2, BusinessTravelJitneyLogger businessTravelJitneyLogger2, GPSPermissionGetter gpsPermissionGetter2, ExploreNagivationInterface nagivationInterface2, ExplorePerformanceAnalytics perfAnalytics2, ExploreDataStore exploreDataStore2) {
        this.dataRepository = dataRepository2;
        this.gpsPermissionGetter = gpsPermissionGetter2;
        this.bus = bus2;
        this.wishListManager = wishListManager2;
        this.businessTravelJitneyLogger = businessTravelJitneyLogger2;
        this.businessTravelAccountManager = businessTravelAccountManager2;
        this.nagivationInterface = nagivationInterface2;
        this.perfAnalytics = perfAnalytics2;
        this.filters = new ExploreFilters(savedState);
        this.savedSearchController = new ExploreSavedSearchController(this, accountManager, savedState);
        this.exploreDataStore = exploreDataStore2;
        if (savedState != null) {
            IcepickWrapper.restoreInstanceState(this, savedState);
            this.tabs = exploreDataStore2.restoreTabs();
            this.seeAllTabs = exploreDataStore2.restoreSeeAllTabs();
            this.currentPlaylist = exploreDataStore2.restoreCurrentPlaylist();
            if (this.tabs == null) {
                this.tabs = new TabMap();
            }
            if (this.seeAllTabs == null) {
                this.seeAllTabs = new TabMap();
            }
        }
        this.dataRepository.init(requestManager, savedState, this);
        bus2.register(this);
    }

    public void onDestroy() {
        this.bus.unregister(this);
        this.handler.removeCallbacksAndMessages(null);
    }

    public void addDataChangedListener(ExploreDataChangedListener listener) {
        Check.state(this.dataChangedListenerSet.add(listener), "listener was already added to set");
    }

    public void removeDataChangedListener(ExploreDataChangedListener listener) {
        Check.state(this.dataChangedListenerSet.remove(listener), "listener did not exist in set");
    }

    public ExploreData getData() {
        return this.data;
    }

    public TopLevelSearchParams getTopLevelSearchParams() {
        return this.seeAllTopLevelSearchParams != null ? this.seeAllTopLevelSearchParams : this.topLevelSearchParams;
    }

    public String getPlaceId() {
        if (!hasHomesMetadata() || getHomesMetadata().getGeography() == null) {
            return null;
        }
        return getHomesMetadata().getGeography().getPlaceId();
    }

    public String getSearchSessionId() {
        if (hasHomesMetadata()) {
            return getHomesMetadata().getSearch().getSearchSessionId();
        }
        return null;
    }

    public String getSearchId() {
        if (hasHomesMetadata()) {
            return getHomesMetadata().getSearch().getSearchId();
        }
        return null;
    }

    public String getFederatedSearchSessionId() {
        if (this.data.hasMetaData()) {
            return this.data.exploreMetaData().getFederatedSearchSessionId();
        }
        return null;
    }

    public String getFederatedSearchId() {
        if (this.data.hasMetaData()) {
            return this.data.exploreMetaData().getFederatedSearchId();
        }
        return null;
    }

    public void onSaveInstanceState(Bundle outState) {
        this.exploreDataStore.saveTabs(this.tabs);
        this.exploreDataStore.saveSeeAllTabs(this.seeAllTabs);
        this.exploreDataStore.saveCurrentPlaylist(this.currentPlaylist);
        IcepickWrapper.saveInstanceState(this, outState);
        this.filters.onSaveInstanceState(outState);
        this.dataRepository.onSaveInstanceState(outState);
        this.savedSearchController.onSaveInstanceState(outState);
    }

    public void setCheckInCheckOutDates(AirDate checkIn, AirDate checkOut) {
        boolean wasNightly = getTopLevelSearchParams().isNightly();
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().checkInDate(checkIn).checkOutDate(checkOut).build();
        if (wasNightly != this.topLevelSearchParams.isNightly()) {
            this.filters.getHomesSearchFilters().clearPrice();
        }
        onSearchParamsChanged();
    }

    public void setSearchTerm(C5809SearchInputType inputType, String searchTerm, String autocompletePlaceId, String selectedVerticalId) {
        this.wishListManager.clearLastUpdatedWishList();
        this.businessTravelAccountManager.setUsingBTRFilter(false);
        this.savedSearchController.reset();
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().searchInputType(inputType).searchTerm(searchTerm).mapBounds(null).autocompletePlaceId(autocompletePlaceId).build();
        this.selectedVerticalFromSearch = selectedVerticalId;
        onSearchParamsChanged();
    }

    public void setMapBounds(MapBounds mapBounds) {
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().mapBounds(mapBounds).build();
        onSearchParamsChanged();
    }

    public void setIntentSource(String intentSource2) {
        this.intentSource = intentSource2;
    }

    public void updateFromSavedSearch(C5809SearchInputType inputType, SavedSearch savedSearch) {
        this.wishListManager.clearLastUpdatedWishList();
        this.businessTravelAccountManager.setUsingBTRFilter(false);
        SearchParams searchParams = savedSearch.getSearchParams();
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().searchTerm(searchParams.getLocation()).mapBounds(null).searchInputType(inputType).autocompletePlaceId(searchParams.getPlaceId()).checkInDate(searchParams.getCheckin()).checkOutDate(searchParams.getCheckout()).guestDetails(searchParams.getGuestDetails()).build();
        this.filters.getHomesSearchFilters().updateFromSearchParams(savedSearch.getSearchParams(), true);
        this.savedSearchController.updateFrom(savedSearch);
        onSearchParamsChanged();
    }

    public void updateFromSearchParams(SearchParams searchParams) {
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().searchTerm(searchParams.getLocation()).checkInDate(searchParams.getCheckin()).checkOutDate(searchParams.getCheckout()).guestDetails(searchParams.getGuestDetails()).build();
        this.filters.getHomesSearchFilters().updateFromSearchParams(searchParams);
        this.filters.getExperienceSearchFilters().updateFromSearchParams(searchParams);
        this.tabIdFromDeeplink = searchParams.getTabId();
    }

    public C5809SearchInputType getSearchInputType() {
        return this.topLevelSearchParams.searchInputType();
    }

    public boolean hasSearchTerm() {
        return !TextUtils.isEmpty(getTopLevelSearchParams().searchTerm());
    }

    public void setGuestData(GuestDetails guestData) {
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().guestDetails(guestData).build();
        onSearchParamsChanged();
    }

    public void onDestinationClicked(String searchTerm) {
        ExploreSeeAllInfo seeAllInfo = new ExploreSeeAllInfo();
        SeeAllInfoQuery seeAllInfoQuery = new SeeAllInfoQuery();
        seeAllInfoQuery.setTopLevelParams(TopLevelSearchParams.builder().searchTerm(searchTerm).build());
        seeAllInfo.setQuery(seeAllInfoQuery);
        seeAllInfo.setTabId(this.nagivationInterface.getActiveTabId());
        setSeeAllInfo(seeAllInfo);
    }

    public void updateUserLocationAndRefreshResultsIfNeeded(Location location) {
        float distanceInMeters = 0.0f;
        if (!(location == null || this.userLocation == null)) {
            distanceInMeters = location.distanceTo(this.userLocation);
        }
        setUserLocation(location);
        if (distanceInMeters > USER_LOCATION_HARD_REFRESH_THRESHOLD_IN_METERS) {
            fetchExploreTabs();
        }
    }

    public void setUserLocation(Location location) {
        this.userLocation = location;
    }

    public Location getUserLocation() {
        return this.userLocation;
    }

    public void setSeeAllInfo(ExploreSeeAllInfo seeAllInfo) {
        String tabId;
        if (seeAllInfo != null) {
            if (!seeAllInfo.getQuery().isEmpty()) {
                this.perfAnalytics.trackTabsLoadStart();
                this.data = this.data.toBuilder().seeAllInfo(seeAllInfo).build();
                configureSeeAllFiltersAndSearchParams(seeAllInfo);
                tabId = seeAllInfo.getTabId();
                fetchExploreTabs(tabId, false);
            } else {
                tabId = seeAllInfo.getTabId();
            }
            notifyTabsUpdated(tabId, false);
        }
    }

    private void configureSeeAllFiltersAndSearchParams(ExploreSeeAllInfo seeAllInfo) {
        SeeAllInfoQuery query = seeAllInfo.getQuery();
        TopLevelSearchParams searchParams = query.getTopLevelParams();
        if (searchParams != null && !searchParams.isEmpty()) {
            if (this.seeAllTopLevelSearchParams == null) {
                this.metadataBeforeSeeAllMode = this.data.exploreMetaData();
            }
            this.seeAllTopLevelSearchParams = searchParams;
        }
        if (Tab.EXPERIENCE.isSameAs(seeAllInfo.getTabId()) && query.getExperienceFilters() != null) {
            this.filters.setExperienceSearchFilters(query.getExperienceFilters(), this.data.isInSeeAllMode());
        } else if (!Tab.PLACES.isSameAs(seeAllInfo.getTabId()) || query.getGuidebookFilters() == null) {
            if (Tab.HOME.isSameAs(seeAllInfo.getTabId())) {
            }
        } else {
            this.filters.setPlacesSearchFilters(query.getGuidebookFilters(), this.data.isInSeeAllMode());
        }
    }

    public void reset() {
        if (isInSeeAllMode()) {
            exitSeeAllMode();
        }
        this.data = ExploreData.builder().build();
        this.topLevelSearchParams = TopLevelSearchParams.builder().build();
        this.filters.resetAllFilters();
        this.wishListManager.clearLastUpdatedWishList();
        this.businessTravelAccountManager.setUsingBTRFilter(false);
        this.savedSearchController.reset();
        onSearchParamsChanged();
    }

    private void onSearchParamsChanged() {
        this.searchParamsModifiedAt = AirDateTime.now();
        if (isInSeeAllMode()) {
            exitSeeAllMode(true);
        }
        fetchExploreTabs();
        for (ExploreDataChangedListener listener : this.dataChangedListenerSet) {
            listener.onSearchParamsUpdated();
        }
        this.perfAnalytics.trackTabsLoadStart();
    }

    public void exitSeeAllMode() {
        exitSeeAllMode(false);
    }

    public void exitSeeAllMode(boolean persistSeeAllVerticalFilters) {
        this.seeAllTabs = new TabMap();
        this.filters.exitSeeAllMode(this.data.seeAllInfo().getTabId(), persistSeeAllVerticalFilters);
        this.data = this.data.toBuilder().seeAllInfo(null).exploreMetaData(this.metadataBeforeSeeAllMode).build();
        this.seeAllTopLevelSearchParams = null;
        this.metadataBeforeSeeAllMode = null;
        this.dataRepository.cancelExploreTabsRequest();
        notifyTabsUpdated(this.nagivationInterface.getActiveTabId(), false);
    }

    public AirDateTime getSearchParamsModifiedAt() {
        return this.searchParamsModifiedAt;
    }

    public void fetchHomeTabMedataDebounced(SearchFilters searchFilters) {
        fetchTabMetaDataDebounced(Tab.HOME, searchFilters, this.filters.getExperienceSearchFilters(), this.filters.getPlacesSearchFilters());
    }

    public void fetchExperienceTabMedataDebounced(ExperienceFilters expSearchFilters) {
        fetchTabMetaDataDebounced(Tab.EXPERIENCE, this.filters.getHomesSearchFilters(), expSearchFilters, this.filters.getPlacesSearchFilters());
    }

    private void fetchTabMetaDataDebounced(Tab toFetch, SearchFilters searchFilters, ExperienceFilters experienceSearchFilters, PlaceFilters placesSearchFilters) {
        String tabId = toFetch.getTabId();
        if (findTabForId(tabId) != null) {
            if (!this.handler.hasMessages(0, this.refreshFiltersRunnableToken)) {
                fetchTabMetaData(tabId, searchFilters, experienceSearchFilters, placesSearchFilters);
                return;
            }
            this.handler.removeCallbacksAndMessages(this.refreshFiltersRunnableToken);
            this.handler.postAtTime(ExploreDataController$$Lambda$1.lambdaFactory$(this, tabId, searchFilters, experienceSearchFilters, placesSearchFilters), this.refreshFiltersRunnableToken, SystemClock.uptimeMillis() + 1000);
        }
    }

    /* access modifiers changed from: private */
    public void fetchTabMetaData(String tabId, SearchFilters searchFilters, ExperienceFilters experienceSearchFilters, PlaceFilters placesSearchFilters) {
        if (findTabForId(tabId) != null) {
            this.dataRepository.fetchTabMetaData(tabId, getTopLevelSearchParams(), searchFilters, experienceSearchFilters, placesSearchFilters, this.data.seeAllInfo(), getFederatedSearchSessionId(), getSearchSessionId(), this.intentSource);
        }
    }

    public void saveSearchToServerIfOutdated() {
        this.savedSearchController.saveToServerIfOutdated();
    }

    public void fetchExploreTabs() {
        String tabId;
        if (this.tabIdFromDeeplink != null) {
            tabId = this.tabIdFromDeeplink;
        } else if (this.selectedVerticalFromSearch != null) {
            tabId = this.selectedVerticalFromSearch;
        } else {
            tabId = this.nagivationInterface.getActiveTabId();
        }
        fetchExploreTabs(tabId, this.nagivationInterface.isMapMode());
        this.tabIdFromDeeplink = null;
        this.selectedVerticalFromSearch = null;
    }

    public void fetchExplorePlaylist(long playlistId) {
        this.dataRepository.fetchExplorePlaylist(playlistId);
    }

    private void fetchExploreTabs(String activeTabId, boolean inMapMode2) {
        this.dataRepository.fetchExploreTabs(getTopLevelSearchParams(), this.filters, this.data.seeAllInfo(), activeTabId, inMapMode2, this.userLocation, this.intentSource);
    }

    public boolean areTabsLoading() {
        return this.dataRepository.areTabsLoading();
    }

    public boolean isFetchingTabMetaData() {
        return this.dataRepository.isFetchingTabMetaData() || this.handler.hasMessages(0, this.refreshFiltersRunnableToken);
    }

    public void cancelMetadataRequest() {
        this.handler.removeCallbacksAndMessages(null);
        this.dataRepository.cancelMetadataRequest();
    }

    public TabMap getTabs() {
        if (isInSeeAllMode()) {
            return this.seeAllTabs;
        }
        return this.tabs;
    }

    public boolean isInSeeAllMode() {
        return this.data.seeAllInfo() != null;
    }

    public boolean isTabSectionLoading(Tab tab) {
        return isTabSectionLoading(tab.getTabId());
    }

    public boolean isTabSectionLoading(ExploreTab tab) {
        return isTabSectionLoading(tab.getTabId());
    }

    public boolean isTabSectionLoading(String tabId) {
        return this.dataRepository.isTabSectionLoading(tabId);
    }

    public String getTabIdAtPosition(int position) {
        List<String> tabList = new ArrayList<>(getTabs().keySet());
        if (tabList.isEmpty() || position >= tabList.size()) {
            return null;
        }
        return (String) tabList.get(position);
    }

    public ExploreTab findTab(Tab tab) {
        return findTabForId(tab.getTabId());
    }

    public ExploreTab findTabForId(String tabId) {
        return (ExploreTab) getTabs().get(tabId);
    }

    public int getPositionOfTabId(String tabId) {
        return new ArrayList<>(getTabs().keySet()).indexOf(tabId);
    }

    public SearchMetaData getHomesMetadata() {
        ExploreTab homeTab = findTab(Tab.HOME);
        if (homeTab == null) {
            return null;
        }
        return homeTab.getHomeTabMetadata();
    }

    public int getListingsCount() {
        SearchMetaData searchMetaData = getHomesMetadata();
        if (searchMetaData != null) {
            return searchMetaData.getListingsCount();
        }
        return -1;
    }

    public int getBusinessTravelReadyListingCount() {
        SearchMetaData searchMetaData = getHomesMetadata();
        if (searchMetaData != null) {
            return searchMetaData.getFacets().getBusinessTravelReadyAvailabilityFacetCount();
        }
        return -1;
    }

    public ExperiencesMetaData getExperiencesMetaData() {
        ExploreTab experiencesTab = findTab(Tab.EXPERIENCE);
        if (experiencesTab == null) {
            return null;
        }
        return experiencesTab.getExperienceTabMetadata();
    }

    public ForYouMetaData getForYouMetaData() {
        ExploreTab forYouTab = findTab(Tab.ALL);
        if (forYouTab == null) {
            return null;
        }
        return forYouTab.getForYouMetaData();
    }

    public int getExperiencesCount() {
        ExperiencesMetaData experiencesMetaData = getExperiencesMetaData();
        if (experiencesMetaData != null) {
            return experiencesMetaData.getCount();
        }
        return -1;
    }

    public boolean hasHomesMetadata() {
        return getHomesMetadata() != null;
    }

    public int getHomesCount() {
        if (hasHomesMetadata()) {
            return getHomesMetadata().getListingsCount();
        }
        return -1;
    }

    public String getSectionIdForRecommendationItem(RecommendationItem item) {
        ExploreTab forYouTab = (ExploreTab) getTabs().get(Tab.ALL.getTabId());
        if (forYouTab == null) {
            return null;
        }
        for (ExploreSection section : forYouTab.getSections()) {
            if (!ListUtils.isEmpty((Collection<?>) section.getRecommendationItems()) && section.getRecommendationItems().contains(item)) {
                return section.getSectionId();
            }
        }
        return null;
    }

    public BusinessTravelReadyFilterCriteria getBusinessTravelReadyFilterCriteria() {
        if (getHomesMetadata() != null) {
            return getHomesMetadata().getSearch().getBusinessTravelReadyData().businessTravelReadyFilterCriteria();
        }
        return null;
    }

    public void fetchTab(Tab tab) {
        fetchTab(tab.getTabId());
    }

    public void fetchTab(String tabId) {
        this.perfAnalytics.trackSingleTabLoadStart(tabId);
        ExploreTab tab = findTabForId(tabId);
        if (tab != null) {
            tab.clearData();
        }
        fetchNextPageForTab(tabId, null);
        notifyTabContentUpdated(tabId, false, null);
    }

    public void retryPaginationRequest(String tabId) {
        fetchNextPageForTab(tabId, ((ExploreTab) getTabs().get(tabId)).getPaginationMetadata());
    }

    public void fetchNextPageForTab(String tabId, PaginationMetadata paginationMetadata) {
        this.dataRepository.fetchNextPageForTab(tabId, paginationMetadata, getTopLevelSearchParams(), this.filters, this.data.seeAllInfo(), getFederatedSearchSessionId(), getSearchSessionId(), this.nagivationInterface.isMapMode(), this.userLocation, this.intentSource);
    }

    public int getDetailFiltersCount(String tabId) {
        return this.filters.getDetailFiltersCount(tabId);
    }

    public void resetFiltersForTab(String tabId) {
        this.filters.resetFiltersForTab(tabId);
        this.topLevelSearchParams = getTopLevelSearchParams().toBuilder().mapBounds(null).build();
        onSearchParamsChanged();
    }

    public void setHomesSearchFiltersAndFetchTab(SearchFilters searchFilters) {
        this.filters.setHomesSearchFilters(searchFilters);
        fetchTab(Tab.HOME);
    }

    public void setExperienceSearchFiltersAndFetchTab(ExperienceFilters searchFilters) {
        this.filters.setExperienceSearchFilters(searchFilters, this.data.isInSeeAllMode());
        fetchTab(Tab.EXPERIENCE);
    }

    public void setPlacesSearchFiltersAndFetchTab(PlaceFilters searchFilters) {
        this.filters.setPlacesSearchFilters(searchFilters, this.data.isInSeeAllMode());
        fetchTab(Tab.PLACES);
    }

    public SearchFilters getHomesSearchFilters() {
        return this.filters.getHomesSearchFilters();
    }

    public ExperienceFilters getExperienceSearchFilters() {
        return this.filters.getExperienceSearchFilters();
    }

    public PlaceFilters getPlacesSearchFilters() {
        return this.filters.getPlacesSearchFilters();
    }

    /* access modifiers changed from: protected */
    public void notifyTabContentUpdated(String tabId, boolean fromNetwork, NetworkException exception) {
        for (ExploreDataChangedListener listener : getDataChangedListenerSetSafe()) {
            listener.onTabContentUpdated(tabId, fromNetwork, exception);
        }
    }

    private void notifyTabsUpdated(String currentTabId, boolean fromNetwork) {
        for (ExploreDataChangedListener listener : getDataChangedListenerSetSafe()) {
            listener.onTabsLoaded(currentTabId, fromNetwork);
        }
    }

    private void notifyMetaDataChanged(String tabId) {
        for (ExploreDataChangedListener listener : getDataChangedListenerSetSafe()) {
            listener.onTabMetadataUpdated(tabId);
        }
    }

    private void notifyExplorePlaylistLoaded() {
        for (ExploreDataChangedListener listener : getDataChangedListenerSetSafe()) {
            listener.onExplorePlaylistLoaded();
        }
    }

    public boolean showUrgencyMessage(SearchUrgencyCommitment urgencyData) {
        return urgencyData != null && urgencyData.isTypeSupported();
    }

    public List<ExploreDataChangedListener> getDataChangedListenerSetSafe() {
        return new ArrayList(this.dataChangedListenerSet);
    }

    public void askForGPSPermission() {
        this.gpsPermissionGetter.askForGPSPermission();
    }

    public void optIntoNotification(ExplorePromotion promotion) {
        this.dataRepository.updateOptInNotification(promotion);
    }

    private void updateBaseTabFromResponse(ExploreTab responseTab) {
        ExploreTab tab = (ExploreTab) getTabs().get(responseTab.getTabId());
        if (tab != null) {
            appendTabResponse(tab, responseTab);
        }
    }

    private void appendTabResponse(ExploreTab tab, ExploreTab responseTab) {
        tab.getSections().addAll(Math.min(tab.getSections().size(), tab.getPaginationMetadata() == null ? 0 : tab.getPaginationMetadata().getSectionOffset()), responseTab.getSections());
        tab.setPaginationMetadata(responseTab.getPaginationMetadata());
        tab.setHomeTabMetadata(responseTab.getHomeTabMetadata());
        tab.setExperienceTabMetadata(responseTab.getExperienceTabMetadata());
        tab.setPlaceTabMetadata(responseTab.getPlaceTabMetadata());
        tab.setEmptyStateMetadata(responseTab.getEmptyStateMetadata());
    }

    private void setErrorOnTabIfPresent(String tabId, boolean hasError) {
        ExploreTab tab = findTabForId(tabId);
        if (tab != null) {
            tab.setHasError(hasError);
        }
    }

    public void onFamilyFriendlyRoomTypeFilterItemClicked() {
        List<C6120RoomType> roomTypes = Lists.newArrayList((E[]) new C6120RoomType[]{C6120RoomType.EntireHome});
        SearchFilters searchFilters = getHomesSearchFilters();
        searchFilters.setRoomTypes(roomTypes);
        searchFilters.addAmenity(Amenity.FamilyFriendly);
        setHomesSearchFiltersAndFetchTab(searchFilters);
    }

    public void onLongTermStayFilterItemClicked() {
        SearchFilters searchFilters = getHomesSearchFilters();
        searchFilters.addAmenity(Amenity.WirelessInternet);
        searchFilters.addAmenity(Amenity.Kitchen);
        searchFilters.addAmenity(Amenity.Washer);
        setHomesSearchFiltersAndFetchTab(searchFilters);
    }

    public void onAmenityInlineFilterItemClicked(InlineSearchFeedFilterItem item) {
        Amenity amenity = Amenity.forId((int) item.getId());
        SearchFilters searchFilters = getHomesSearchFilters();
        searchFilters.addAmenity(amenity);
        setHomesSearchFiltersAndFetchTab(searchFilters);
    }

    public void onAmenityRemovalSuggestionClicked(Amenity amenity) {
        SearchFilters searchFilters = getHomesSearchFilters();
        searchFilters.removeAmenity(amenity);
        setHomesSearchFiltersAndFetchTab(searchFilters);
    }

    public void onInstantBookRemovalSuggestionClicked() {
        SearchFilters searchFilters = getHomesSearchFilters();
        searchFilters.setIsInstantBookOnly(false);
        setHomesSearchFiltersAndFetchTab(searchFilters);
    }

    public void onFilterSuggestionPillClicked(FilterSuggestionItem suggestionItem) {
        SearchFilters homesSearchFilters = getHomesSearchFilters();
        FilterSuggestionFilters suggestedFilters = suggestionItem.getFilters();
        if (suggestedFilters.hasRoomTypes()) {
            homesSearchFilters.setRoomTypes(suggestedFilters.getRoomTypes());
        }
        if (suggestedFilters.hasSetInstantBookOnly() && suggestedFilters.isInstantBookOnly().booleanValue()) {
            homesSearchFilters.setIsInstantBookOnly(true);
        }
        if (suggestedFilters.hasMinPrice()) {
            homesSearchFilters.setMinPrice(suggestedFilters.getMinPrice());
        }
        if (suggestedFilters.hasMaxPrice()) {
            homesSearchFilters.setMaxPrice(suggestedFilters.getMaxPrice());
        }
        if (suggestedFilters.hasNumBedrooms()) {
            homesSearchFilters.setNumBedrooms(suggestedFilters.getNumBedrooms());
        }
        if (suggestedFilters.hasNumBeds()) {
            homesSearchFilters.setNumBeds(suggestedFilters.getNumBeds());
        }
        if (suggestedFilters.hasAmenities()) {
            for (Amenity amenity : suggestedFilters.getAmenities()) {
                homesSearchFilters.addAmenity(amenity);
            }
        }
        setHomesSearchFiltersAndFetchTab(homesSearchFilters);
    }

    public void onFilterRemovalPillClicked(FilterSuggestionItem item) {
        SearchFilters homesSearchFilters = getHomesSearchFilters();
        FilterSuggestionFilters suggestedFilters = item.getFilters();
        if (suggestedFilters.hasRoomTypes()) {
            ArrayList<C6120RoomType> updatedRoomTypes = Lists.newArrayList((Iterable<? extends E>) homesSearchFilters.getRoomTypes());
            updatedRoomTypes.removeAll(suggestedFilters.getRoomTypes());
            homesSearchFilters.setRoomTypes(updatedRoomTypes);
        }
        if (suggestedFilters.hasSetInstantBookOnly() && suggestedFilters.isInstantBookOnly().booleanValue()) {
            homesSearchFilters.setIsInstantBookOnly(false);
        }
        if (suggestedFilters.hasMinPrice()) {
            homesSearchFilters.setMinPrice(0);
        }
        if (suggestedFilters.hasMaxPrice()) {
            homesSearchFilters.setMaxPrice(0);
        }
        if (suggestedFilters.hasNumBedrooms()) {
            homesSearchFilters.setNumBedrooms(0);
        }
        if (suggestedFilters.hasNumBeds()) {
            homesSearchFilters.setNumBeds(0);
        }
        if (suggestedFilters.hasAmenities()) {
            for (Amenity amenity : suggestedFilters.getAmenities()) {
                homesSearchFilters.removeAmenity(amenity);
            }
        }
        setHomesSearchFiltersAndFetchTab(homesSearchFilters);
    }

    private boolean isOverWarningLimit() {
        if (this.currentPlaylist != null) {
            return IcepickWrapper.isOverWarningLimitByteSize(this.tabs, this.seeAllTabs, this.currentPlaylist);
        }
        return IcepickWrapper.isOverWarningLimitByteSize(this.tabs, this.seeAllTabs);
    }

    private void storeSearchedCity(ExploreTab responseTab) {
        String city = "";
        if (responseTab != null && responseTab.getHomeTabMetadata() != null && responseTab.getHomeTabMetadata().getGeography() != null) {
            try {
                city = responseTab.getHomeTabMetadata().getGeography().getCity();
                CoreApplication.instance().component().localPushNotificationManager().p2StoreLocation(city);
            } catch (JSONException e) {
                C0715L.m1191e(TAG, String.format("LocalPushNotification fails to store recent p2, city = %s. ", new Object[]{city}) + e.getMessage());
            }
        }
    }

    private void overrideInstantBookFilter() {
        boolean isIbOverrideOn = hasHomesMetadata() && getHomesMetadata().isIbOverrideOn();
        if (!this.filters.getHomesSearchFilters().hasSetInstantBookOnly() && isIbOverrideOn) {
            this.filters.getHomesSearchFilters().setIsInstantBookOnly(true);
        }
    }

    public void onTabsLoaded(ExploreResponse response) {
        boolean cached = response.metadata.isCached();
        String searchSessionId = getSearchSessionId();
        TabMap tabMap = new TabMap();
        for (ExploreTab tab : response.tabs) {
            tabMap.put(tab.getTabId(), tab);
        }
        if (isInSeeAllMode()) {
            this.seeAllTabs = tabMap;
        } else {
            this.tabs = tabMap;
        }
        overrideInstantBookFilter();
        this.data = this.data.toBuilder().exploreMetaData(response.metaData).build();
        notifyTabsUpdated(response.metaData.getLandingTabId(), !cached);
        this.perfAnalytics.trackTabsLoadSuccess(cached, getSearchId());
        this.savedSearchController.saveToServerIfOutdated();
        int numBTRListings = getBusinessTravelReadyListingCount();
        if (numBTRListings != -1) {
            this.businessTravelJitneyLogger.logBTRFilterNumListings((long) numBTRListings);
        }
    }

    public void onPlaylistLoaded(ExplorePlaylistResponse response) {
        this.currentPlaylist = ListUtils.isEmpty((Collection<?>) response.playlists) ? null : (ExplorePlaylist) response.playlists.get(0);
        notifyExplorePlaylistLoaded();
    }

    public void onTabsFailed(NetworkException e) {
        for (ExploreDataChangedListener listener : getDataChangedListenerSetSafe()) {
            listener.onTabsLoadError(e);
        }
        this.perfAnalytics.trackTabsLoadFailed();
    }

    public void onSpecificTabLoaded(String tabId, ExploreTabResponse response) {
        boolean z = false;
        boolean cached = response.metadata.isCached();
        updateBaseTabFromResponse(response.getTab());
        setErrorOnTabIfPresent(tabId, false);
        if (!cached) {
            z = true;
        }
        notifyTabContentUpdated(tabId, z, null);
        this.perfAnalytics.trackSingleTabLoadSuccess(cached, tabId, getSearchId());
    }

    public void onSpecificTabFailed(String tabId, NetworkException e) {
        setErrorOnTabIfPresent(tabId, true);
        notifyTabContentUpdated(tabId, false, e);
        this.perfAnalytics.trackSingleTabLoadFailed(tabId);
    }

    public Set<String> getTabsKeySet() {
        return getTabs().keySet();
    }

    public boolean hasNullTabs() {
        return getTabs() == null;
    }

    public ExplorePlaylist getCurrentExplorePlaylist() {
        return this.currentPlaylist;
    }

    public void onMetadataLoaded(ExploreTabResponse response) {
        ExploreTab responseTab = response.getTab();
        updateBaseTabFromResponse(responseTab);
        notifyMetaDataChanged(responseTab.getTabId());
    }

    public void onMetadataFailed(AirRequestNetworkException e) {
        notifyMetaDataChanged(((ExploreTabRequest) e.request()).getTabId());
    }
}
