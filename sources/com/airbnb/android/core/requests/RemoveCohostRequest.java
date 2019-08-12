package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.RemoveCohostResponse;
import java.lang.reflect.Type;

public class RemoveCohostRequest extends BaseRequestV2<RemoveCohostResponse> {
    private final String listingManagerId;

    public RemoveCohostRequest(String listingManagerId2) {
        this.listingManagerId = listingManagerId2;
    }

    public String getPath() {
        return "listing_managers/" + this.listingManagerId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return RemoveCohostResponse.class;
    }
}
