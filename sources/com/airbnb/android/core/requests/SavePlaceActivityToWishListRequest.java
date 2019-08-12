package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class SavePlaceActivityToWishListRequest extends BaseRequestV2<BaseResponse> {
    public final long placeActivityId;
    public final long wishListId;

    private class Body {
        @JsonProperty
        final long collectionId;
        @JsonProperty
        final long placeActivityId;

        private Body() {
            this.collectionId = SavePlaceActivityToWishListRequest.this.wishListId;
            this.placeActivityId = SavePlaceActivityToWishListRequest.this.placeActivityId;
        }
    }

    public SavePlaceActivityToWishListRequest(WishList wishList, long placeActivityId2) {
        this.wishListId = wishList.getId();
        this.placeActivityId = placeActivityId2;
    }

    public Object getBody() {
        return new Body();
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_activities";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
