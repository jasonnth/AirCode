package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import java.lang.reflect.Type;

public class RemoveTripFromWishListRequest extends BaseRequestV2<BaseResponse> {
    private final long tripId;
    private final WishList wishList;

    public RemoveTripFromWishListRequest(WishList wishList2, long tripId2) {
        this.wishList = wishList2;
        this.tripId = tripId2;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_mt_templates/" + this.wishList.getId() + "/" + this.tripId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
