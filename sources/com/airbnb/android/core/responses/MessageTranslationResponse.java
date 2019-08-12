package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.TranslatedMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MessageTranslationResponse extends BaseResponse {
    @JsonProperty("messages")
    public List<TranslatedMessage> translatedMessages;
}
