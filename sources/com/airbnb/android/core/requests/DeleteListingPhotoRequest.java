package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import java.lang.reflect.Type;

public class DeleteListingPhotoRequest extends BaseRequestV2<BaseResponse> {
    private final long listingId;
    private final long photoId;

    public DeleteListingPhotoRequest(long listingId2, long photoId2) {
        this.listingId = listingId2;
        this.photoId = photoId2;
    }

    public String getPath() {
        return "listing_photos/" + this.listingId + "_" + this.photoId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
