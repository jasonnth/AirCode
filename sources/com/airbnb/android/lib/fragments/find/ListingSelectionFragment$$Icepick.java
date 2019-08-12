package com.airbnb.android.lib.fragments.find;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.fragments.find.ListingSelectionFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ListingSelectionFragment$$Icepick<T extends ListingSelectionFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9570H = new Helper("com.airbnb.android.lib.fragments.find.ListingSelectionFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.user = (User) f9570H.getParcelable(state, "user");
            target.listings = f9570H.getParcelableArrayList(state, "listings");
            target.selectedListing = (Listing) f9570H.getParcelable(state, "selectedListing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9570H.putParcelable(state, "user", target.user);
        f9570H.putParcelableArrayList(state, "listings", target.listings);
        f9570H.putParcelable(state, "selectedListing", target.selectedListing);
    }
}
