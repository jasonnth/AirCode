package com.airbnb.android.explore.controllers;

import android.location.Location;
import android.os.Bundle;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.ExplorePromotion;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.PaginationMetadata;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.ObservableRequestManager;
import com.airbnb.android.core.responses.ExplorePlaylistResponse;
import com.airbnb.android.core.responses.ExploreResponse;
import com.airbnb.android.core.responses.ExploreTabResponse;
import com.airbnb.android.explore.data.ExploreFilters;
import com.airbnb.android.explore.requests.ExploreOptInNotificationRequest;
import com.airbnb.android.explore.requests.ExplorePlaylistRequest;
import com.airbnb.android.explore.requests.ExploreRequest;
import com.airbnb.android.explore.requests.ExploreTabMetadataRequest;
import com.airbnb.android.explore.requests.ExploreTabRequest;
import com.airbnb.rxgroups.RequestSubscription;
import icepick.State;
import java.util.ArrayList;
import java.util.Map;
import p032rx.Observer;

public class ExploreDataRepositoryImpl implements ExploreDataRepository {
    @State
    boolean areExploreTabsLoading;
    private ExploreDataRepositoryCallback callback;
    private String currentSpecificTabRequestTabId;
    final RequestListener<ExploreTabResponse> exploreSpecificTabListener = new C0699RL().onResponse(ExploreDataRepositoryImpl$$Lambda$3.lambdaFactory$(this)).onError(ExploreDataRepositoryImpl$$Lambda$4.lambdaFactory$(this)).onComplete(ExploreDataRepositoryImpl$$Lambda$5.lambdaFactory$(this)).build();
    final RequestListener<ExploreResponse> exploreTabsListener = new C0699RL().onResponse(ExploreDataRepositoryImpl$$Lambda$1.lambdaFactory$(this)).onError(ExploreDataRepositoryImpl$$Lambda$2.lambdaFactory$(this)).build();
    @State
    ArrayList<String> loadingTabSections = new ArrayList<>();
    final RequestListener<ExplorePlaylistResponse> playlistRequestListener = new C0699RL().onResponse(ExploreDataRepositoryImpl$$Lambda$6.lambdaFactory$(this)).onError(ExploreDataRepositoryImpl$$Lambda$7.lambdaFactory$()).build();
    protected RequestManager requestManager;
    final RequestListener<ExploreTabResponse> tabMetaDataRequestListener = new C0699RL().onResponse(ExploreDataRepositoryImpl$$Lambda$8.lambdaFactory$(this)).onError(ExploreDataRepositoryImpl$$Lambda$9.lambdaFactory$(this)).build();
    private final Map<String, RequestSubscription> tabRequests = new ArrayMap();

    public void init(ObservableRequestManager requestManager2, Bundle savedState, ExploreDataRepositoryCallback callback2) {
        if (this.requestManager != null) {
            throw new IllegalStateException("You may only initialize ExploreDataRepository once.");
        }
        this.requestManager = requestManager2;
        this.callback = callback2;
        if (savedState != null) {
            IcepickWrapper.restoreInstanceState(this, savedState);
        }
        requestManager2.subscribe(this);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void fetchExploreTabs(TopLevelSearchParams topLevelSearchParams, ExploreFilters searchFilters, ExploreSeeAllInfo seeAllInfo, String tabId, boolean inMapMode, Location location, String searchIntentSource) {
        assertCallback();
        this.areExploreTabsLoading = true;
        ExploreRequest.newInstance(topLevelSearchParams, searchFilters, seeAllInfo, null, null, tabId, inMapMode, location, searchIntentSource).withListener((Observer) this.exploreTabsListener).doubleResponse().execute(this.requestManager);
    }

    public void fetchTabMetaData(String tabId, TopLevelSearchParams topLevelSearchParams, SearchFilters searchFilters, ExperienceFilters experienceSearchFilters, PlaceFilters placesSearchFilters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, String searchIntentSource) {
        assertCallback();
        new ExploreTabMetadataRequest(tabId, topLevelSearchParams, experienceSearchFilters, searchFilters, placesSearchFilters, seeAllInfo, federatedSearchSessionId, searchSessionId, searchIntentSource).withListener((Observer) this.tabMetaDataRequestListener).doubleResponse().execute(this.requestManager);
    }

    public void fetchNextPageForTab(String tabId, PaginationMetadata paginationMetadata, TopLevelSearchParams topLevelSearchParams, ExploreFilters filters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, boolean inMapMode, Location location, String searchIntentSource) {
        if (this.tabRequests.containsKey(tabId)) {
            ((RequestSubscription) this.tabRequests.get(tabId)).cancel();
        }
        addLoadingTab(tabId);
        this.tabRequests.put(tabId, this.requestManager.executeWithTag(ExploreTabRequest.forTab(tabId, topLevelSearchParams, paginationMetadata, filters, seeAllInfo, federatedSearchSessionId, searchSessionId, inMapMode, location).withListener((Observer) this.exploreSpecificTabListener), getTabSectionTag(tabId)));
    }

    public void cancelExploreTabsRequest() {
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.exploreTabsListener, ExploreRequest.class)) {
            this.requestManager.cancelRequest((BaseRequestListener<T>) this.exploreTabsListener, ExploreRequest.class);
            this.areExploreTabsLoading = false;
        }
    }

    public boolean areTabsLoading() {
        return this.areExploreTabsLoading;
    }

    public boolean isFetchingTabMetaData() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.tabMetaDataRequestListener, ExploreTabMetadataRequest.class);
    }

    public void cancelMetadataRequest() {
        this.requestManager.cancelRequest((BaseRequestListener<T>) this.tabMetaDataRequestListener, ExploreTabMetadataRequest.class);
    }

    public boolean isTabSectionLoading(String tabId) {
        return this.loadingTabSections.contains(tabId);
    }

    public void fetchExplorePlaylist(long collectionId) {
        ExplorePlaylistRequest.newInstance(collectionId).doubleResponse().withListener(this.playlistRequestListener).execute(this.requestManager);
    }

    public void updateOptInNotification(ExplorePromotion promotion) {
        new ExploreOptInNotificationRequest(promotion.getData()).execute(this.requestManager);
    }

    private void addLoadingTab(String tabId) {
        if (!this.loadingTabSections.contains(tabId)) {
            this.loadingTabSections.add(tabId);
        }
    }

    private void removeLoadingTab(String tabId) {
        this.loadingTabSections.remove(tabId);
    }

    private void assertCallback() {
        if (this.callback == null) {
            throw new IllegalStateException("You must set a callback.");
        }
    }

    private String getTabSectionTag(String tabId) {
        return "explore_tab_" + tabId;
    }

    static /* synthetic */ void lambda$new$0(ExploreDataRepositoryImpl exploreDataRepositoryImpl, ExploreResponse response) {
        exploreDataRepositoryImpl.areExploreTabsLoading = false;
        exploreDataRepositoryImpl.callback.onTabsLoaded(response);
    }

    static /* synthetic */ void lambda$new$1(ExploreDataRepositoryImpl exploreDataRepositoryImpl, AirRequestNetworkException e) {
        exploreDataRepositoryImpl.areExploreTabsLoading = false;
        exploreDataRepositoryImpl.callback.onTabsFailed(e);
    }

    static /* synthetic */ void lambda$new$2(ExploreDataRepositoryImpl exploreDataRepositoryImpl, ExploreTabResponse response) {
        exploreDataRepositoryImpl.currentSpecificTabRequestTabId = response.getTab().getTabId();
        exploreDataRepositoryImpl.removeLoadingTab(exploreDataRepositoryImpl.currentSpecificTabRequestTabId);
        exploreDataRepositoryImpl.callback.onSpecificTabLoaded(exploreDataRepositoryImpl.currentSpecificTabRequestTabId, response);
    }

    static /* synthetic */ void lambda$new$3(ExploreDataRepositoryImpl exploreDataRepositoryImpl, AirRequestNetworkException e) {
        exploreDataRepositoryImpl.currentSpecificTabRequestTabId = ((ExploreTabRequest) e.request()).getTabId();
        exploreDataRepositoryImpl.removeLoadingTab(exploreDataRepositoryImpl.currentSpecificTabRequestTabId);
        exploreDataRepositoryImpl.callback.onSpecificTabFailed(exploreDataRepositoryImpl.currentSpecificTabRequestTabId, e);
    }

    static /* synthetic */ void lambda$new$4(ExploreDataRepositoryImpl exploreDataRepositoryImpl, Boolean complete) {
        exploreDataRepositoryImpl.removeLoadingTab(exploreDataRepositoryImpl.currentSpecificTabRequestTabId);
        exploreDataRepositoryImpl.tabRequests.remove(exploreDataRepositoryImpl.currentSpecificTabRequestTabId);
    }

    static /* synthetic */ void lambda$new$6(AirRequestNetworkException e) {
    }
}
