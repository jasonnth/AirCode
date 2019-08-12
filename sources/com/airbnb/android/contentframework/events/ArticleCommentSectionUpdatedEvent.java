package com.airbnb.android.contentframework.events;

public class ArticleCommentSectionUpdatedEvent {
    public final long articleId;

    public ArticleCommentSectionUpdatedEvent(long articleId2) {
        this.articleId = articleId2;
    }
}
