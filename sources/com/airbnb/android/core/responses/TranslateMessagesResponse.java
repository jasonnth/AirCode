package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TranslateMessagesResponse extends BaseResponse {
    @JsonProperty("translated_messages")
    public List<TranslateMessageResponse> translatedMessages;
}
