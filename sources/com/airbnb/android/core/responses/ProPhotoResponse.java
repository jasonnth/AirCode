package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProPhotoResponse extends BaseResponse {
    @JsonProperty("photography")
    ProPhotographyStatus proPhotographyStatus;

    public static class ProPhotographyStatus {
        @JsonProperty("listing_id")
        public long listingId;
        @JsonProperty("status")
        public String proPhotoStatus;
    }

    public String getProPhotoStatus() {
        return this.proPhotographyStatus.proPhotoStatus;
    }

    public long getListingId() {
        return this.proPhotographyStatus.listingId;
    }
}
