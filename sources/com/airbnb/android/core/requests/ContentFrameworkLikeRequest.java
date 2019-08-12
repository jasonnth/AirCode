package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ContentFrameworkLikeUnlikeResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public final class ContentFrameworkLikeRequest extends BaseRequestV2<ContentFrameworkLikeUnlikeResponse> {

    /* renamed from: id */
    private final long f8485id;
    private final LikeType likeType;

    public enum LikeType {
        Article,
        Comment
    }

    public static ContentFrameworkLikeRequest requestForComment(long commentId) {
        return new ContentFrameworkLikeRequest(LikeType.Comment, commentId);
    }

    public static ContentFrameworkLikeRequest requestForArticle(long articleId) {
        return new ContentFrameworkLikeRequest(LikeType.Article, articleId);
    }

    private ContentFrameworkLikeRequest(LikeType likeType2, long id) {
        this.likeType = likeType2;
        this.f8485id = id;
    }

    public Object getBody() {
        return Strap.make().mo11639kv("likeable_type", this.likeType == LikeType.Article ? "article" : "comment").mo11638kv("likeable_id", this.f8485id);
    }

    public Type successResponseType() {
        return ContentFrameworkLikeUnlikeResponse.class;
    }

    public String getPath() {
        return "content_framework_likes";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
