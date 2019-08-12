package com.airbnb.android.login.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Login;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginResponse extends BaseResponse {
    @JsonProperty("login")
    public Login login;
}
