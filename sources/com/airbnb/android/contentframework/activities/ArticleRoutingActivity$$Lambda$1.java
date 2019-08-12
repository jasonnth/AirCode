package com.airbnb.android.contentframework.activities;

import com.airbnb.android.contentframework.responses.GetArticleResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ArticleRoutingActivity$$Lambda$1 implements Action1 {
    private final ArticleRoutingActivity arg$1;

    private ArticleRoutingActivity$$Lambda$1(ArticleRoutingActivity articleRoutingActivity) {
        this.arg$1 = articleRoutingActivity;
    }

    public static Action1 lambdaFactory$(ArticleRoutingActivity articleRoutingActivity) {
        return new ArticleRoutingActivity$$Lambda$1(articleRoutingActivity);
    }

    public void call(Object obj) {
        this.arg$1.viewArticleNatively(((GetArticleResponse) obj).article);
    }
}
