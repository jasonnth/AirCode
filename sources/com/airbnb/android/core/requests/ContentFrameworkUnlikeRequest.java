package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.requests.ContentFrameworkLikeRequest.LikeType;
import com.airbnb.android.core.responses.ContentFrameworkLikeUnlikeResponse;
import java.lang.reflect.Type;

public final class ContentFrameworkUnlikeRequest extends BaseRequestV2<ContentFrameworkLikeUnlikeResponse> {

    /* renamed from: id */
    private final long f8486id;
    private final LikeType likeType;

    public static ContentFrameworkUnlikeRequest requestForComment(long commentId) {
        return new ContentFrameworkUnlikeRequest(LikeType.Comment, commentId);
    }

    public static ContentFrameworkUnlikeRequest requestForArticle(long articleId) {
        return new ContentFrameworkUnlikeRequest(LikeType.Article, articleId);
    }

    private ContentFrameworkUnlikeRequest(LikeType likeType2, long id) {
        this.likeType = likeType2;
        this.f8486id = id;
    }

    public Type successResponseType() {
        return ContentFrameworkLikeUnlikeResponse.class;
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder("content_framework_likes");
        sb.append(this.likeType == LikeType.Article ? "/article/" : "/comment/");
        sb.append(this.f8486id);
        return sb.toString();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
