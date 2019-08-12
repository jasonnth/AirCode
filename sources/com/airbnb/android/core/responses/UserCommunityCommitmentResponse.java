package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.UserCommunityCommitment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCommunityCommitmentResponse {
    @JsonProperty("user_community_commitment")
    public UserCommunityCommitment userCommunityCommitment;
}
