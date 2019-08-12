package com.airbnb.android.contentframework.fragments;

import android.os.Bundle;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.core.models.Article;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class StoryDetailViewFragment$$Icepick<T extends StoryDetailViewFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8044H = new Helper("com.airbnb.android.contentframework.fragments.StoryDetailViewFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.articleId = f8044H.getLong(state, "articleId");
            target.article = (Article) f8044H.getParcelable(state, "article");
            target.partialArticle = (Article) f8044H.getParcelable(state, "partialArticle");
            target.onCreateTime = f8044H.getLong(state, "onCreateTime");
            target.likeUnlikeArticleRequestInFlight = f8044H.getBoolean(state, "likeUnlikeArticleRequestInFlight");
            target.optionalMessageShown = f8044H.getBoolean(state, "optionalMessageShown");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8044H.putLong(state, "articleId", target.articleId);
        f8044H.putParcelable(state, "article", target.article);
        f8044H.putParcelable(state, "partialArticle", target.partialArticle);
        f8044H.putLong(state, "onCreateTime", target.onCreateTime);
        f8044H.putBoolean(state, "likeUnlikeArticleRequestInFlight", target.likeUnlikeArticleRequestInFlight);
        f8044H.putBoolean(state, "optionalMessageShown", target.optionalMessageShown);
    }
}
