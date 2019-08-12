package com.airbnb.android.nestedlistings;

import android.os.Bundle;
import com.airbnb.android.nestedlistings.NestedListingsActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class NestedListingsActivity$$Icepick<T extends NestedListingsActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10416H = new Helper("com.airbnb.android.nestedlistings.NestedListingsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.nestedListingsById = (HashMap) f10416H.getSerializable(state, "nestedListingsById");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10416H.putSerializable(state, "nestedListingsById", target.nestedListingsById);
    }
}
