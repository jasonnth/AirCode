package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemandCountsResponse extends BaseResponse {
    @JsonProperty("metadata")
    Metadata metaData;

    public static class Metadata {
        @JsonProperty("bookings")
        int newBookings;
        @JsonProperty("page_views")
        int pageViews;
    }

    public int getPageViews() {
        if (this.metaData == null) {
            return 0;
        }
        return this.metaData.pageViews;
    }

    public int getNewBookings() {
        if (this.metaData == null) {
            return 0;
        }
        return this.metaData.newBookings;
    }
}
