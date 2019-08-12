package com.airbnb.android.contentframework.events;

public class ArticleEngagementUpdatedEvent {
    public final long articleId;
    public final int commentCount;
    public final int likeCount;

    public ArticleEngagementUpdatedEvent(long articleId2, int likeCount2, int commentCount2) {
        this.articleId = articleId2;
        this.likeCount = likeCount2;
        this.commentCount = commentCount2;
    }
}
