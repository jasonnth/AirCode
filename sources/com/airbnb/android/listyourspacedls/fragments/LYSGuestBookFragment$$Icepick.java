package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestBookFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class LYSGuestBookFragment$$Icepick<T extends LYSGuestBookFragment> extends LYSBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9933H = new Helper("com.airbnb.android.listyourspacedls.fragments.LYSGuestBookFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.previousInstantBookCategory = f9933H.getString(state, "previousInstantBookCategory");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9933H.putString(state, "previousInstantBookCategory", target.previousInstantBookCategory);
    }
}
