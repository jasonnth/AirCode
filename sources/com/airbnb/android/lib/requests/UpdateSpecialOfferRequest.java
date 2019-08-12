package com.airbnb.android.lib.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.SpecialOfferResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateSpecialOfferRequest extends BaseRequestV2<SpecialOfferResponse> {
    private final RequestBody requestBody;
    private final long specialOfferId;

    private static class RequestBody {
        @JsonProperty("block_instant_booking")
        final boolean blockInstantBooking;

        RequestBody(boolean blockInstantBooking2) {
            this.blockInstantBooking = blockInstantBooking2;
        }
    }

    public UpdateSpecialOfferRequest(long specialOfferId2, boolean blockInstantBooking) {
        this.specialOfferId = specialOfferId2;
        this.requestBody = new RequestBody(blockInstantBooking);
    }

    public String getPath() {
        return "special_offers/" + this.specialOfferId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return SpecialOfferResponse.class;
    }

    public Object getBody() {
        return this.requestBody;
    }
}
