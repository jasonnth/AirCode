package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import com.airbnb.android.fixit.fragments.FixItItemCommentFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class FixItItemCommentFragment$$Icepick<T extends FixItItemCommentFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8700H = new Helper("com.airbnb.android.fixit.fragments.FixItItemCommentFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hostComment = f8700H.getString(state, "hostComment");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8700H.putString(state, "hostComment", target.hostComment);
    }
}
