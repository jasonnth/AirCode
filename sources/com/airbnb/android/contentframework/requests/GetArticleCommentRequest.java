package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.contentframework.responses.GetArticleCommentResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public final class GetArticleCommentRequest extends BaseRequestV2<GetArticleCommentResponse> {
    private final Strap queryParams;

    private GetArticleCommentRequest(Strap queryParams2) {
        this.queryParams = queryParams2;
    }

    public static GetArticleCommentRequest forArticle(long articleId, int limit, int offset) {
        return new GetArticleCommentRequest(Strap.make().mo11638kv("article_id", articleId).mo11639kv(TimelineRequest.ARG_FORMAT, "default").mo11637kv(TimelineRequest.ARG_LIMIT, limit).mo11637kv(TimelineRequest.ARG_OFFSET, offset));
    }

    public static GetArticleCommentRequest hotCommentsForArticle(long articleId, int limit) {
        return new GetArticleCommentRequest(Strap.make().mo11638kv("article_id", articleId).mo11639kv(TimelineRequest.ARG_FORMAT, "default").mo11637kv(TimelineRequest.ARG_LIMIT, limit).mo11639kv("_order", "popular"));
    }

    public Type successResponseType() {
        return GetArticleCommentResponse.class;
    }

    public String getPath() {
        return "content_framework_comments";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }
}
