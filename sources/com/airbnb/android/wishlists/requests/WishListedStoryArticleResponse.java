package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.WishListedArticle;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WishListedStoryArticleResponse extends BaseResponse {
    @JsonProperty("collection_articles")
    public List<WishListedArticle> articles;
}
