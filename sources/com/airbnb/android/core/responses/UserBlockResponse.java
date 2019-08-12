package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.UserBlock;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBlockResponse extends BaseResponse {
    @JsonProperty("user_block")
    public UserBlock userBlock;
}
