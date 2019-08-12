package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SendEmailConfirmationCodeResponse extends BaseResponse {
    @JsonProperty
    public int error;
    @JsonProperty
    public String errorCode;
    @JsonProperty
    public String errorMessage;
}
