package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateMessageResponse extends BaseResponse {
    @JsonProperty("message")
    public Post post;
    public long threadId;
}
