package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.interfaces.TabbedResponse;
import com.airbnb.android.core.models.ExploreTab;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExploreTabResponse extends BaseResponse implements TabbedResponse {
    @JsonProperty("explore_tabs")
    private List<ExploreTab> tabs;

    public ExploreTab getTab() {
        if (this.tabs.isEmpty()) {
            return null;
        }
        return (ExploreTab) this.tabs.get(0);
    }

    public List<ExploreTab> getTabs() {
        return this.tabs;
    }
}
