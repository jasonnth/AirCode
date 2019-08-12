package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import java.lang.reflect.Type;

public class RemoveListingFromWishListRequest extends BaseRequestV2<BaseResponse> {
    private final long listingId;
    private final WishList wishList;

    public RemoveListingFromWishListRequest(WishList wishList2, long listingId2) {
        this.wishList = wishList2;
        this.listingId = listingId2;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "wishlisted_listings/" + this.wishList.getId() + "/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
