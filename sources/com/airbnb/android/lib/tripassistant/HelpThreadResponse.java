package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HelpThread;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HelpThreadResponse extends BaseResponse {
    @JsonProperty("help_thread")
    public HelpThread helpThread;
}
