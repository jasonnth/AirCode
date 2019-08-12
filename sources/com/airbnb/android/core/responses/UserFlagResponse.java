package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFlagResponse extends BaseResponse {
    @JsonProperty("user_flag")
    public UserFlag flag;
}
