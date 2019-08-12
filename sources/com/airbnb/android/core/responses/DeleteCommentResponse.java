package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ArticleComment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteCommentResponse extends BaseResponse {
    @JsonProperty("content_framework_comment")
    public ArticleComment comment;
}
