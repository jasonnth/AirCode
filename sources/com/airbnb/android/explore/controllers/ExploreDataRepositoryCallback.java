package com.airbnb.android.explore.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.responses.ExplorePlaylistResponse;
import com.airbnb.android.core.responses.ExploreResponse;
import com.airbnb.android.core.responses.ExploreTabResponse;
import java.util.Set;

public interface ExploreDataRepositoryCallback {
    Set<String> getTabsKeySet();

    boolean hasNullTabs();

    void onMetadataFailed(AirRequestNetworkException airRequestNetworkException);

    void onMetadataLoaded(ExploreTabResponse exploreTabResponse);

    void onPlaylistLoaded(ExplorePlaylistResponse explorePlaylistResponse);

    void onSpecificTabFailed(String str, NetworkException networkException);

    void onSpecificTabLoaded(String str, ExploreTabResponse exploreTabResponse);

    void onTabsFailed(NetworkException networkException);

    void onTabsLoaded(ExploreResponse exploreResponse);
}
