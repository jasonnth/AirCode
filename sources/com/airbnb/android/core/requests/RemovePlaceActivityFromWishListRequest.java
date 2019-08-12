package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import java.lang.reflect.Type;

public class RemovePlaceActivityFromWishListRequest extends BaseRequestV2<BaseResponse> {

    /* renamed from: id */
    private final long f8490id;
    private final WishList wishList;

    public RemovePlaceActivityFromWishListRequest(WishList wishList2, long id) {
        this.wishList = wishList2;
        this.f8490id = id;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_activities/" + this.wishList.getId() + "/" + this.f8490id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
