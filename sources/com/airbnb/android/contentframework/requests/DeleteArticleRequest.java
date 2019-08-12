package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.contentframework.responses.DeleteArticleResponse;
import java.lang.reflect.Type;

public class DeleteArticleRequest extends BaseRequestV2<DeleteArticleResponse> {
    private final long storyId;

    public DeleteArticleRequest(long storyId2) {
        this.storyId = storyId2;
    }

    public Type successResponseType() {
        return DeleteArticleResponse.class;
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append("content_framework_articles/");
        sb.append(this.storyId);
        return sb.toString();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
