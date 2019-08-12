package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.MparticleUser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MParticleCustomerIdResponse extends BaseResponse {
    @JsonProperty("mparticle_user")
    public MparticleUser mParticleUser;
}
