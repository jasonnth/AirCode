package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.responses.WishListMembershipsResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class DeleteWishlistMembershipRequest extends BaseRequestV2<WishListMembershipsResponse> {
    private final User user;
    private final long wishlistId;

    public DeleteWishlistMembershipRequest(WishList wishList, User user2, BaseRequestListener<WishListMembershipsResponse> listener) {
        withListener((Observer) listener);
        this.wishlistId = wishList.getId();
        this.user = user2;
    }

    public String getPath() {
        return "wishlist_memberships/" + this.wishlistId + "/" + this.user.getId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public User getUser() {
        return this.user;
    }

    public Type successResponseType() {
        return WishListMembershipsResponse.class;
    }
}
