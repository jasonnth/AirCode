package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HostStandardMetrics;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HostStandardsResponse extends BaseResponse {
    @JsonProperty("host_standard")
    public HostStandardMetrics hostStandardMetrics;
}
