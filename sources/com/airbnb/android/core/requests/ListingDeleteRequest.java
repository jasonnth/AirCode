package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ListingResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class ListingDeleteRequest extends BaseRequestV2<ListingResponse> {
    private final long listingId;

    public ListingDeleteRequest(long listingId2, BaseRequestListener<ListingResponse> listener) {
        withListener((Observer) listener);
        this.listingId = listingId2;
    }

    public String getPath() {
        return "listings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return ListingResponse.class;
    }
}
