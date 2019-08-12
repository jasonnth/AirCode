package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.contentframework.responses.StoryFeedResponse;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class StoryFeedRequest extends BaseRequestV2<StoryFeedResponse> {
    private static final int PAGE_LIMIT = 20;
    private final String paginationCursor;
    private final QueryStrap queryParams;

    public static StoryFeedRequest forSearch(String searchTerm, String paginationCursor2) {
        return new StoryFeedRequest(paginationCursor2, QueryStrap.make().mo7895kv("search_term", searchTerm));
    }

    public static StoryFeedRequest forFeed(String paginationCursor2) {
        QueryStrap params = QueryStrap.make().mo7895kv("feature", "community_articles");
        if (paginationCursor2 == null) {
            params.mo7895kv(TimelineRequest.ARG_FORMAT, "with_tabs");
        }
        return new StoryFeedRequest(paginationCursor2, params);
    }

    public static StoryFeedRequest forUser(long userId, String paginationCursor2) {
        return new StoryFeedRequest(paginationCursor2, QueryStrap.make().mo7894kv("author_id", userId));
    }

    public static StoryFeedRequest forTopTileFeed(StoryFeedTopTile topTile, String paginationCursor2) {
        return new StoryFeedRequest(paginationCursor2, QueryStrap.make().mix((Map<String, String>) topTile.getQueryParams()));
    }

    private StoryFeedRequest(String paginationCursor2, QueryStrap queryParams2) {
        this.queryParams = queryParams2;
        this.paginationCursor = paginationCursor2;
    }

    public Collection<Query> getQueryParams() {
        return this.queryParams.mo7895kv("cursor", this.paginationCursor).mo7893kv(TimelineRequest.ARG_LIMIT, 20);
    }

    public String getPath() {
        return "content_framework_articles";
    }

    public Type successResponseType() {
        return StoryFeedResponse.class;
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }
}
