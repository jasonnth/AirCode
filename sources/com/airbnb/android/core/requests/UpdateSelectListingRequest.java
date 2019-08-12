package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.SelectListingResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UpdateSelectListingRequest extends BaseRequestV2<SelectListingResponse> {
    private final long listingId;
    private final Object requestBody;

    UpdateSelectListingRequest(long listingId2, Object requestBody2) {
        this.listingId = listingId2;
        this.requestBody = requestBody2;
    }

    public static UpdateSelectListingRequest forField(long listingId2, String field, Object value) {
        return new UpdateSelectListingRequest(listingId2, Strap.make().mo11639kv(field, value.toString()));
    }

    public Object getBody() {
        return this.requestBody;
    }

    public String getPath() {
        return "select_listings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return SelectListingResponse.class;
    }
}
