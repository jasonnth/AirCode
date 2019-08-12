package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishListMembership;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WishlistMembershipResponse extends BaseResponse {
    @JsonProperty("wishlist_membership")
    public WishListMembership wishListMembership;
}
