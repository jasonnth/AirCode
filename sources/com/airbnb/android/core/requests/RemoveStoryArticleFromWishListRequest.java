package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import java.lang.reflect.Type;

public class RemoveStoryArticleFromWishListRequest extends BaseRequestV2<BaseResponse> {

    /* renamed from: id */
    private final long f8491id;
    private final WishList wishList;

    public RemoveStoryArticleFromWishListRequest(WishList wishList2, long id) {
        this.wishList = wishList2;
        this.f8491id = id;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_articles/" + this.wishList.getId() + "/" + this.f8491id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
