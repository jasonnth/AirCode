package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Attachment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttachmentResponse extends BaseResponse {
    @JsonProperty("attachment")
    public Attachment attachment;
}
