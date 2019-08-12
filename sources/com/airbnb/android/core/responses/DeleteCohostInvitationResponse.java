package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.CohostInvitation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteCohostInvitationResponse {
    @JsonProperty("cohost_invitation")
    public CohostInvitation cohostInvitation;
}
