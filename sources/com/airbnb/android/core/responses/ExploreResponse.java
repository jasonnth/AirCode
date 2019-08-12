package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.interfaces.TabbedResponse;
import com.airbnb.android.core.models.ExploreMetaData;
import com.airbnb.android.core.models.ExploreTab;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExploreResponse extends BaseResponse implements TabbedResponse {
    @JsonProperty("metadata")
    public ExploreMetaData metaData;
    @JsonProperty("explore_tabs")
    public List<ExploreTab> tabs;

    public List<ExploreTab> getTabs() {
        return this.tabs;
    }
}
