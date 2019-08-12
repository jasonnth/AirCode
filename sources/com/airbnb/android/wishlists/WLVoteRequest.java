package com.airbnb.android.wishlists;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.wishlists.WishListItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

class WLVoteRequest extends BaseRequestV2<BaseResponse> {
    private final Body body = new Body();
    final WishListItem item;
    /* access modifiers changed from: private */
    public final WLItemVote newVote;
    final WLItemVote originalVote;
    private final long userId;

    private class Body {
        @JsonProperty
        final long entityId;
        @JsonProperty
        final String entityType;
        @JsonProperty
        final boolean isUpVote;

        private Body() {
            this.entityId = WLVoteRequest.this.item.getId();
            this.isUpVote = WLVoteRequest.this.newVote == WLItemVote.Up;
            this.entityType = WLVoteRequest.this.getEntityType();
        }
    }

    WLVoteRequest(WishListItem item2, User user, WLItemVote originalVote2, WLItemVote newVote2) {
        this.item = item2;
        this.originalVote = originalVote2;
        this.newVote = newVote2;
        this.userId = user.getId();
    }

    /* access modifiers changed from: private */
    public String getEntityType() {
        switch (this.item.getItemType()) {
            case Home:
                return "collection_hosting";
            case Place:
                return "collection_place";
            case PlaceActivity:
                return "collection_activity";
            case Trip:
                return "collection_mt_template";
            default:
                throw new IllegalStateException("unknown type: " + this.item.getItemType());
        }
    }

    public Body getBody() {
        if (isDelete()) {
            return null;
        }
        return this.body;
    }

    public Collection<Query> getQueryParams() {
        if (isDelete()) {
            return QueryStrap.make().mo7895kv("entity_type", getEntityType());
        }
        return super.getQueryParams();
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        if (isDelete()) {
            return "wishlist_votes/" + this.item.getId() + "/" + this.userId;
        }
        return "wishlist_votes";
    }

    public RequestMethod getMethod() {
        return isDelete() ? RequestMethod.DELETE : RequestMethod.POST;
    }

    private boolean isDelete() {
        return this.newVote == WLItemVote.None;
    }
}
