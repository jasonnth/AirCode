package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class SaveTripToWishListRequest extends BaseRequestV2<BaseResponse> {
    public final long tripId;
    public final long wishListId;

    private class Body {
        @JsonProperty
        final long collectionId;
        @JsonProperty
        final long mtTemplateId;

        private Body() {
            this.collectionId = SaveTripToWishListRequest.this.wishListId;
            this.mtTemplateId = SaveTripToWishListRequest.this.tripId;
        }
    }

    public SaveTripToWishListRequest(WishList wishList, long tripId2) {
        this.wishListId = wishList.getId();
        this.tripId = tripId2;
    }

    public Object getBody() {
        return new Body();
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_mt_templates";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
