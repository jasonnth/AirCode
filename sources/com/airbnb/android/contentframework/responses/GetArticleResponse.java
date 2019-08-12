package com.airbnb.android.contentframework.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Article;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class GetArticleResponse extends BaseResponse {
    @JsonProperty("content_framework_article")
    public Article article;
}
