package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class UserWrapperResponse extends BaseResponse {
    @WrappedObject("user")
    @JsonProperty("user")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public User user;
}
