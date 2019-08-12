package com.airbnb.android.contentframework.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class StoryFeedResponse extends BaseResponse {
    @JsonProperty("content_framework_articles")
    public List<Article> articles;
    @JsonProperty("metadata")
    public Metadata metadata;

    private static class Metadata {
        @JsonProperty("page_info")
        public PageInfo pageInfo;
        @JsonProperty("top_tiles")
        public List<StoryFeedTopTile> topTiles;

        private Metadata() {
        }
    }

    private static class PageInfo {
        @JsonProperty("has_next_page")
        public boolean hasNextPage;
        @JsonProperty("tail_cursor")
        public String tailCursor;

        private PageInfo() {
        }
    }

    public boolean hasNextPage() {
        return this.metadata.pageInfo.hasNextPage;
    }

    public String tailCursor() {
        return this.metadata.pageInfo.tailCursor;
    }

    public List<StoryFeedTopTile> getTopTiles() {
        return this.metadata.topTiles;
    }
}
