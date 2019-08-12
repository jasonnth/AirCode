package com.airbnb.android.core.adapters.find;

import com.airbnb.android.core.analytics.FindTweenAnalytics;

/* renamed from: com.airbnb.android.core.adapters.find.SearchInputType */
public enum C5809SearchInputType {
    CurrentLocation(FindTweenAnalytics.SEARCH_TYPE_CURRENT_LOCATION),
    Anywhere(FindTweenAnalytics.SEARCH_TYPE_ANYWHERE),
    SavedSearch("saved_search"),
    AutoComplete(FindTweenAnalytics.SEARCH_TYPE_AUTOCOMPLETE_SUGGESTION),
    PopularDestination(FindTweenAnalytics.SEARCH_TYPE_POPULAR_DESTINATION),
    Manual(FindTweenAnalytics.SEARCH_TYPE_MANUAL),
    CurrentCity(FindTweenAnalytics.SEARCH_TYPE_CURRENT_CITY);
    
    public final String loggingString;

    private C5809SearchInputType(String loggingString2) {
        this.loggingString = loggingString2;
    }
}
