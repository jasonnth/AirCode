package com.airbnb.android.contentframework.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ArticleComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class GetArticleCommentResponse extends BaseResponse {
    @JsonProperty("content_framework_comments")
    public List<ArticleComment> comments;
    @JsonProperty("metadata")
    public MetaData metaData;

    public static class MetaData {
        @JsonProperty("count")
        public int count;
    }
}
