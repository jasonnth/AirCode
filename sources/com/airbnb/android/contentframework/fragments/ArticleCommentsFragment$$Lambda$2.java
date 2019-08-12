package com.airbnb.android.contentframework.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ArticleCommentsFragment$$Lambda$2 implements Action1 {
    private final ArticleCommentsFragment arg$1;

    private ArticleCommentsFragment$$Lambda$2(ArticleCommentsFragment articleCommentsFragment) {
        this.arg$1 = articleCommentsFragment;
    }

    public static Action1 lambdaFactory$(ArticleCommentsFragment articleCommentsFragment) {
        return new ArticleCommentsFragment$$Lambda$2(articleCommentsFragment);
    }

    public void call(Object obj) {
        ArticleCommentsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
