package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HostStatsAverageRatingResponse extends BaseResponse {
    @JsonProperty("user")
    Wrapper wrapper;

    public static class Wrapper {
        @JsonProperty("average_decimal_host_rating")
        double averageHostRating;
        @JsonProperty("similar_host_rating")
        Double similarHostAverageRating;
    }

    public Double getAverageRating() {
        if (this.wrapper == null) {
            return null;
        }
        return Double.valueOf(this.wrapper.averageHostRating);
    }

    public Double getSimilarHostAverageRating() {
        if (this.wrapper == null) {
            return null;
        }
        return this.wrapper.similarHostAverageRating;
    }
}
