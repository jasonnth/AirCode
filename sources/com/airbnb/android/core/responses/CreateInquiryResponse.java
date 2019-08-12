package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.MessageThreadV2;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateInquiryResponse extends BaseResponse {
    @JsonProperty("thread")
    private MessageThreadV2 thread;

    public MessageThreadV2 getThread() {
        return this.thread;
    }
}
