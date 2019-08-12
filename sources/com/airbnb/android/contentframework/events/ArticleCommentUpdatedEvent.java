package com.airbnb.android.contentframework.events;

public class ArticleCommentUpdatedEvent {
    public final long articleId;
    public final int commentCount;

    public ArticleCommentUpdatedEvent(long articleId2, int commentCount2) {
        this.articleId = articleId2;
        this.commentCount = commentCount2;
    }
}
