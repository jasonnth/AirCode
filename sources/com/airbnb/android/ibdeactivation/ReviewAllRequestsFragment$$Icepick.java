package com.airbnb.android.ibdeactivation;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.ibdeactivation.ReviewAllRequestsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReviewAllRequestsFragment$$Icepick<T extends ReviewAllRequestsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9159H = new Helper("com.airbnb.android.ibdeactivation.ReviewAllRequestsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hostReason = f9159H.getString(state, "hostReason");
            target.listing = (Listing) f9159H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9159H.putString(state, "hostReason", target.hostReason);
        f9159H.putParcelable(state, "listing", target.listing);
    }
}
