package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.TemplateMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCannedMessageResponse {
    @JsonProperty("template_message")
    public TemplateMessage templateMessage;
}
