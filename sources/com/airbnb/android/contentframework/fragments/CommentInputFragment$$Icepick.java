package com.airbnb.android.contentframework.fragments;

import android.os.Bundle;
import com.airbnb.android.contentframework.fragments.CommentInputFragment;
import com.airbnb.android.core.models.ArticleComment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CommentInputFragment$$Icepick<T extends CommentInputFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8041H = new Helper("com.airbnb.android.contentframework.fragments.CommentInputFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.parentComment = (ArticleComment) f8041H.getParcelable(state, "parentComment");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8041H.putParcelable(state, "parentComment", target.parentComment);
    }
}
