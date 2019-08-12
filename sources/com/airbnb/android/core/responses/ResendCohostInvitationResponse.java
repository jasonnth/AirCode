package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.CohostInvitation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResendCohostInvitationResponse {
    @JsonProperty("cohost_invitation")
    public CohostInvitation cohostInvitation;
}
