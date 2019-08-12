package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ThreadInboxInformation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadInboxInformationResponse extends BaseResponse {
    @JsonProperty("thread")
    public ThreadInboxInformation threadInboxInformation;
}
