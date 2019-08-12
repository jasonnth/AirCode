package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishListMembership;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class WishListMembershipsResponse extends BaseResponse {
    @JsonProperty("wishlist_memberships")
    public List<WishListMembership> wishListMemberships;

    public ArrayList<User> getCollaborators() {
        ArrayList<User> collaborators = new ArrayList<>(this.wishListMemberships.size());
        for (WishListMembership wishListMembership : this.wishListMemberships) {
            collaborators.add(wishListMembership.getUser());
        }
        return collaborators;
    }
}
