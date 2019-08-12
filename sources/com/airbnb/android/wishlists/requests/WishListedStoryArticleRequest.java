package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import retrofit2.Query;

public class WishListedStoryArticleRequest extends BaseRequestV2<WishListedStoryArticleResponse> {
    private static final int NUM_ITEMS_PER_PAGE = 30;
    private final List<Long> storyArticleIds;
    private final long wishlistId;

    public WishListedStoryArticleRequest(WishList wishList) {
        this.wishlistId = wishList.getId();
        this.storyArticleIds = ImmutableList.copyOf((Collection<? extends E>) wishList.getArticleIds());
    }

    public Type successResponseType() {
        return WishListedStoryArticleResponse.class;
    }

    public String getPath() {
        return "collection_articles";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("collection_id", this.wishlistId).mo7895kv(TimelineRequest.ARG_FORMAT, "with_article").mo7893kv(TimelineRequest.ARG_LIMIT, 30);
    }

    public boolean hasSameStoryArticles(WishList wishList) {
        return this.storyArticleIds.equals(wishList.getArticleIds());
    }
}
