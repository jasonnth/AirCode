package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingRoom;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingRoomResponse extends BaseResponse {
    @JsonProperty("listing_room")
    public ListingRoom listingRoom;
}
