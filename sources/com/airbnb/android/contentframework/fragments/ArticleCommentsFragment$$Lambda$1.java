package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.contentframework.responses.GetArticleCommentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ArticleCommentsFragment$$Lambda$1 implements Action1 {
    private final ArticleCommentsFragment arg$1;

    private ArticleCommentsFragment$$Lambda$1(ArticleCommentsFragment articleCommentsFragment) {
        this.arg$1 = articleCommentsFragment;
    }

    public static Action1 lambdaFactory$(ArticleCommentsFragment articleCommentsFragment) {
        return new ArticleCommentsFragment$$Lambda$1(articleCommentsFragment);
    }

    public void call(Object obj) {
        ArticleCommentsFragment.lambda$new$0(this.arg$1, (GetArticleCommentResponse) obj);
    }
}
