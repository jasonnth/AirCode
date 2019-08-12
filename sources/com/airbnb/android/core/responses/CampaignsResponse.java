package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CampaignsResponse extends BaseResponse {
    @JsonProperty("campaigns")
    public List<Campaign> campaigns;

    public static class Campaign {
        @JsonProperty("id")
        public String key;
        @JsonProperty("statuses")
        public Map<String, Object> statuses;
    }
}
