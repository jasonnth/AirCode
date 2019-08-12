package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.WishListResponse;
import java.lang.reflect.Type;

public class DeleteWishListRequest extends BaseRequestV2<WishListResponse> {
    private final long wishlistId;

    public DeleteWishListRequest(long wishlistId2) {
        this.wishlistId = wishlistId2;
    }

    public String getPath() {
        return "wishlists/" + this.wishlistId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return WishListResponse.class;
    }
}
