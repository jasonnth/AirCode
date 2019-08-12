package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.SelectListingResponse;
import java.lang.reflect.Type;

public class GetSelectListingRequest extends BaseRequestV2<SelectListingResponse> {
    private final long listingId;

    private GetSelectListingRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public static GetSelectListingRequest forListingId(long listingId2) {
        return new GetSelectListingRequest(listingId2);
    }

    public String getPath() {
        return "select_listings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return SelectListingResponse.class;
    }
}
