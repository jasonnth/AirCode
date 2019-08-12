package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class SaveListingToWishListRequest extends BaseRequestV2<BaseResponse> {
    private final Body body;

    private class Body {
        @JsonProperty
        final long listingId;
        @JsonProperty
        final long wishlistId;

        private Body(long wishlistId2, long listingId2) {
            this.wishlistId = wishlistId2;
            this.listingId = listingId2;
        }
    }

    public SaveListingToWishListRequest(WishList wishList, long listingId) {
        this.body = new Body(wishList.getId(), listingId);
    }

    public Object getBody() {
        return this.body;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "wishlisted_listings";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
