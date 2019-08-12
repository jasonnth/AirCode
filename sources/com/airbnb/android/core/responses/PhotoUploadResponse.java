package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.models.ListingPhoto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotoUploadResponse extends BaseResponse {
    @JsonProperty("listing_photo")
    public ListingPhoto listingPhoto;
    @JsonProperty("check_in_guide_step")
    public CheckInStep step;
}
