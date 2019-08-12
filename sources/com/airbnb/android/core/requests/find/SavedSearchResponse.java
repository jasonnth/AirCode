package com.airbnb.android.core.requests.find;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SavedSearch;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SavedSearchResponse extends BaseResponse {
    @JsonProperty("saved_search")
    public SavedSearch savedSearch;
}
