package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse extends BaseResponse {
    @JsonProperty("user")
    public User user;
}
