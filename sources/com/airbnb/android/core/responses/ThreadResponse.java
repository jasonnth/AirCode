package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Thread;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadResponse extends BaseResponse {
    @JsonProperty("thread")
    public Thread thread;

    public ThreadResponse() {
    }

    public ThreadResponse(Thread thread2) {
        this.thread = thread2;
    }
}
