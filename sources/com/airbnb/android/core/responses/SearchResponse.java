package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SearchGeography;
import com.airbnb.android.core.models.SearchMetaData;
import com.airbnb.android.core.models.SearchResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SearchResponse extends BaseResponse {
    public List<Listing> listings;
    @JsonProperty("metadata")
    public SearchMetaData searchMetaData;
    @JsonProperty("search_results")
    public List<SearchResult> searchResults;

    public boolean hasSearchResults() {
        return this.searchResults != null && !this.searchResults.isEmpty();
    }

    public SearchGeography getGeography() {
        if (this.searchMetaData != null) {
            return this.searchMetaData.getGeography();
        }
        return null;
    }
}
