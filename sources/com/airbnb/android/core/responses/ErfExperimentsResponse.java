package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Experiment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ErfExperimentsResponse extends BaseResponse {
    @JsonProperty("metadata")
    public ErfExperimentsMetadata erfMetadata;
    @JsonProperty("erf_experiments")
    public List<Experiment> experiments;

    public static class ErfExperimentsMetadata {
        @JsonProperty("timestamp")
        public long timestamp;
    }
}
