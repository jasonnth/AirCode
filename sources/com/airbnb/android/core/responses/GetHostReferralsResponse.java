package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Referree;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetHostReferralsResponse extends BaseResponse {
    @JsonProperty("host_referrals")
    public List<Referree> referrees;
}