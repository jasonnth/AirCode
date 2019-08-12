package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HostStandard;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HostReactivationCopyResponse extends BaseResponse {
    @JsonProperty("host_standard")
    public HostStandard hostStandard;
}
