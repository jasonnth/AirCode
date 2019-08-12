package com.airbnb.android.contentframework.events;

public class ArticleLikeCountUpdatedEvent {
    public final long articleId;
    public final int delta;

    public ArticleLikeCountUpdatedEvent(long articleId2, int delta2) {
        this.articleId = articleId2;
        this.delta = delta2;
    }
}
