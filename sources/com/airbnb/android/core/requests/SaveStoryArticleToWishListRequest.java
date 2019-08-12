package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.WishList;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class SaveStoryArticleToWishListRequest extends BaseRequestV2<BaseResponse> {
    private final Body body;

    static class Body {
        @JsonProperty
        final long articleId;
        @JsonProperty
        final long collectionId;

        public Body(long collectionId2, long articleId2) {
            this.collectionId = collectionId2;
            this.articleId = articleId2;
        }
    }

    public SaveStoryArticleToWishListRequest(WishList wishList, long articleId) {
        this.body = new Body(wishList.getId(), articleId);
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_articles";
    }

    public Body getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
