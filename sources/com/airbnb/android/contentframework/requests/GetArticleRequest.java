package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.contentframework.responses.GetArticleResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public final class GetArticleRequest extends BaseRequestV2<GetArticleResponse> {
    private static final String ARG_VERSION = "version";
    private static final String CURRENT_API_VERSION = "1";
    private final long articleId;

    public GetArticleRequest(long articleId2) {
        this.articleId = articleId2;
    }

    public String getPath() {
        return "content_framework_articles/" + this.articleId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "with_content_v2").mo7895kv("version", "1");
    }

    public Type successResponseType() {
        return GetArticleResponse.class;
    }
}
