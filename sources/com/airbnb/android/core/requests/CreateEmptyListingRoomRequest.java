package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ListingRoomResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateEmptyListingRoomRequest extends BaseRequestV2<ListingRoomResponse> {
    private final ListingRoomRequestBodyType body;

    private static final class ListingRoomRequestBodyType {
        @JsonProperty("listing_id")
        final long listingId;
        @JsonProperty("room_number")
        final int roomNumber;

        ListingRoomRequestBodyType(long listingId2, int roomNumber2) {
            this.listingId = listingId2;
            this.roomNumber = roomNumber2;
        }
    }

    public CreateEmptyListingRoomRequest(long listingId, int roomNumber) {
        this.body = new ListingRoomRequestBodyType(listingId, roomNumber);
    }

    public Object getBody() {
        return this.body;
    }

    public String getPath() {
        return "listing_rooms";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return ListingRoomResponse.class;
    }
}
