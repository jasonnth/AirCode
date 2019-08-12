package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class SavePlaceToWishListRequest extends BaseRequestV2<BaseResponse> {
    public final long placeId;
    public final long wishListId;

    private class Body {
        @JsonProperty
        final long collectionId;
        @JsonProperty
        final long placeId;

        private Body() {
            this.collectionId = SavePlaceToWishListRequest.this.wishListId;
            this.placeId = SavePlaceToWishListRequest.this.placeId;
        }
    }

    public SavePlaceToWishListRequest(WishList wishList, long placeId2) {
        this.wishListId = wishList.getId();
        this.placeId = placeId2;
    }

    public Object getBody() {
        return new Body();
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_places";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
