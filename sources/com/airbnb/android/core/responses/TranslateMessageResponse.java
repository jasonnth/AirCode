package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class TranslateMessageResponse extends BaseResponse {
    @JsonProperty("id")

    /* renamed from: id */
    public long f1091id;
    @JsonProperty("message_id")
    public long messageId;
    @JsonProperty("message")
    public String translatedMessage;
}
