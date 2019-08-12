package com.airbnb.android.contentframework.fragments;

import android.os.Bundle;
import com.airbnb.android.contentframework.fragments.ArticleCommentsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ArticleCommentsFragment$$Icepick<T extends ArticleCommentsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8040H = new Helper("com.airbnb.android.contentframework.fragments.ArticleCommentsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.articleId = f8040H.getLong(state, "articleId");
            target.totalComments = f8040H.getInt(state, "totalComments");
            target.loadedComments = f8040H.getParcelableArrayList(state, "loadedComments");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8040H.putLong(state, "articleId", target.articleId);
        f8040H.putInt(state, "totalComments", target.totalComments);
        f8040H.putParcelableArrayList(state, "loadedComments", target.loadedComments);
    }
}
