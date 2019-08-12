package com.airbnb.android.core.responses;

import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.android.core.models.Login;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AirlockErrorResponse extends ErrorResponse {
    @JsonProperty("client_error_info")
    public ClientErrorInfo clientErrorInfo = new ClientErrorInfo();
    @JsonProperty("login")
    public Login login;
}
