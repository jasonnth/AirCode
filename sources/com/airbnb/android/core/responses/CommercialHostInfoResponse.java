package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.CommercialHostInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CommercialHostInfoResponse {
    @JsonProperty("commercial_host_infos")
    public List<CommercialHostInfo> commercialHostInfos;
}
