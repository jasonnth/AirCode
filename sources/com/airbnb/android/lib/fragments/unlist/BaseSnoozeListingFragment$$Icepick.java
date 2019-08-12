package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.unlist.BaseSnoozeListingFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BaseSnoozeListingFragment$$Icepick<T extends BaseSnoozeListingFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9584H = new Helper("com.airbnb.android.lib.fragments.unlist.BaseSnoozeListingFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.progressCompleteTitle = f9584H.getInt(state, "progressCompleteTitle");
            target.loadingTitle = f9584H.getInt(state, "loadingTitle");
            target.listing = (Listing) f9584H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9584H.putInt(state, "progressCompleteTitle", target.progressCompleteTitle);
        f9584H.putInt(state, "loadingTitle", target.loadingTitle);
        f9584H.putParcelable(state, "listing", target.listing);
    }
}
