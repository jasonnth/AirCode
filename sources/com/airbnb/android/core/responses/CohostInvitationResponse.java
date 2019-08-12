package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CohostInvitation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CohostInvitationResponse extends BaseResponse {
    @JsonProperty("cohost_invitation")
    public CohostInvitation cohostInvitation;
}
