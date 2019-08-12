package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class WishlistsResponse extends BaseResponse {
    @JsonProperty("wishlists")
    List<WishList> wishLists;

    public List<WishList> getWishLists() {
        return new ArrayList(this.wishLists);
    }
}
