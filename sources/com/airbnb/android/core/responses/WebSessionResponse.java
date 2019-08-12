package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.UserWebSession;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebSessionResponse extends BaseResponse {
    @JsonProperty("user_session")
    public UserWebSession userSession;
}
