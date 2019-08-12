package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class DeleteManualVerificationResponse extends BaseResponse {
    @WrappedObject("message")
    @JsonProperty("identity")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public String message;
}
