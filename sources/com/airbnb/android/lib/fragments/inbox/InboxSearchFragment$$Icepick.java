package com.airbnb.android.lib.fragments.inbox;

import android.os.Bundle;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.lib.fragments.inbox.InboxSearchFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class InboxSearchFragment$$Icepick<T extends InboxSearchFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9574H = new Helper("com.airbnb.android.lib.fragments.inbox.InboxSearchFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.inboxType = (InboxType) f9574H.getSerializable(state, "inboxType");
            target.results = f9574H.getParcelableArrayList(state, "results");
            target.currentQuery = f9574H.getString(state, "currentQuery");
            target.forPendingFilter = f9574H.getBoolean(state, "forPendingFilter");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9574H.putSerializable(state, "inboxType", target.inboxType);
        f9574H.putParcelableArrayList(state, "results", target.results);
        f9574H.putString(state, "currentQuery", target.currentQuery);
        f9574H.putBoolean(state, "forPendingFilter", target.forPendingFilter);
    }
}
