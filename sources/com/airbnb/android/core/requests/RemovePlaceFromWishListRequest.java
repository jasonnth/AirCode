package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import java.lang.reflect.Type;

public class RemovePlaceFromWishListRequest extends BaseRequestV2<BaseResponse> {
    private final long placeId;
    private final WishList wishList;

    public RemovePlaceFromWishListRequest(WishList wishList2, long placeId2) {
        this.wishList = wishList2;
        this.placeId = placeId2;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_places/" + this.wishList.getId() + "/" + this.placeId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
