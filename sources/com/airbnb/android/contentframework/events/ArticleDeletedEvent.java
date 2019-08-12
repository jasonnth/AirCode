package com.airbnb.android.contentframework.events;

public class ArticleDeletedEvent {
    public final long articleId;

    public ArticleDeletedEvent(long articleId2) {
        this.articleId = articleId2;
    }
}
