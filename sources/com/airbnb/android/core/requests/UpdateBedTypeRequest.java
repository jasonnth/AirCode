package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.BedTypeResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateBedTypeRequest extends BaseRequestV2<BedTypeResponse> {
    private final long listingId;
    private final BedTypeRequestBody requestBody;
    private final long roomId;

    private static final class BedTypeRequestBody {
        @JsonProperty("quantity")
        final int quantity;
        @JsonProperty("type")
        final String typeServerKey;

        BedTypeRequestBody(String typeServerKey2, int quantity2) {
            this.typeServerKey = typeServerKey2;
            this.quantity = quantity2;
        }
    }

    public UpdateBedTypeRequest(long listingId2, long roomId2, String bedTypeServerKey, int quantity) {
        this.listingId = listingId2;
        this.roomId = roomId2;
        this.requestBody = new BedTypeRequestBody(bedTypeServerKey, quantity);
    }

    public Object getBody() {
        return this.requestBody;
    }

    public String getPath() {
        return "listing_room_amenities/" + this.listingId + "/" + this.roomId + "/" + this.requestBody.typeServerKey;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return BedTypeResponse.class;
    }
}
