package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WishListResponse extends BaseResponse {
    @JsonProperty("wishlist")
    public WishList wishList;
}
