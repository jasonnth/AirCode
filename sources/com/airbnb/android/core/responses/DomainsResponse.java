package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DomainsResponse extends BaseResponse {
    @JsonProperty("domains")
    public List<Domain> mDomains;
}
