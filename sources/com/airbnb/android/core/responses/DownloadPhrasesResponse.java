package com.airbnb.android.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class DownloadPhrasesResponse {
    @JsonProperty
    public Map<String, String> phrases;
}
