package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.fixit.fragments.FixItItemFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class FixItItemFragment$$Icepick<T extends FixItItemFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8701H = new Helper("com.airbnb.android.fixit.fragments.FixItItemFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.item = (FixItItem) f8701H.getParcelable(state, "item");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8701H.putParcelable(state, "item", target.item);
    }
}
