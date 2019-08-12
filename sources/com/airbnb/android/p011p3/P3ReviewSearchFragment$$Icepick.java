package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.p011p3.P3ReviewSearchFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment$$Icepick */
public class P3ReviewSearchFragment$$Icepick<T extends P3ReviewSearchFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10537H = new Helper("com.airbnb.android.p3.P3ReviewSearchFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentQuery = f10537H.getString(state, "currentQuery");
            target.listingId = f10537H.getLong(state, "listingId");
            target.keywords = f10537H.getParcelableArrayList(state, "keywords");
            target.reviewResults = f10537H.getParcelableArrayList(state, "reviewResults");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10537H.putString(state, "currentQuery", target.currentQuery);
        f10537H.putLong(state, "listingId", target.listingId);
        f10537H.putParcelableArrayList(state, "keywords", target.keywords);
        f10537H.putParcelableArrayList(state, "reviewResults", target.reviewResults);
    }
}
