package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.WishlistMembershipResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateWishlistMembershipRequest extends BaseRequestV2<WishlistMembershipResponse> {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("invite_code")
        final String inviteCode;
        @JsonProperty("wishlist_id")
        final long wishlistId;

        RequestBody(long wishlistId2, String inviteCode2) {
            this.wishlistId = wishlistId2;
            this.inviteCode = inviteCode2;
        }
    }

    public CreateWishlistMembershipRequest(long wishlistId, String inviteCode) {
        this.requestBody = new RequestBody(wishlistId, inviteCode);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public String getPath() {
        return "wishlist_memberships";
    }

    public Type successResponseType() {
        return WishlistMembershipResponse.class;
    }
}
