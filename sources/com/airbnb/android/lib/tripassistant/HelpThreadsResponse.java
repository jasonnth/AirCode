package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HelpThread;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HelpThreadsResponse extends BaseResponse {
    @JsonProperty("help_threads")
    public List<HelpThread> helpThreads;
}
