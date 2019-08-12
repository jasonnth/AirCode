package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.InboxSearchResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class InboxSearchResponse extends BaseResponse {
    @JsonProperty("inbox_searches")
    public List<InboxSearchResult> searchResults;
}
