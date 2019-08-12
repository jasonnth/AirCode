package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingRoom;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class ListingRoomsResponse extends BaseResponse {
    @JsonProperty("listing_rooms")
    public ArrayList<ListingRoom> listingRooms;
}
