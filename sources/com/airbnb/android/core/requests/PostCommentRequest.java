package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.PostCommentResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class PostCommentRequest extends BaseRequestV2<PostCommentResponse> {
    private final long articleId;
    private final String content;
    private final long parentCommentId;

    public static PostCommentRequest postCommentForArticle(long articleId2, String content2) {
        return new PostCommentRequest(articleId2, content2, 0);
    }

    public static PostCommentRequest replyToArticleComment(long articleId2, String content2, long parentCommentId2) {
        return new PostCommentRequest(articleId2, content2, parentCommentId2);
    }

    public PostCommentRequest(long articleId2, String content2, long parentCommentId2) {
        this.articleId = articleId2;
        this.content = content2;
        this.parentCommentId = parentCommentId2;
    }

    public Type successResponseType() {
        return PostCommentResponse.class;
    }

    public String getPath() {
        return "content_framework_comments";
    }

    public Object getBody() {
        Strap requestBody = Strap.make().mo11638kv("article_id", this.articleId).mo11639kv("content", this.content);
        if (this.parentCommentId > 0) {
            requestBody.mo11638kv("parent_comment_id", this.parentCommentId);
        }
        return requestBody;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
