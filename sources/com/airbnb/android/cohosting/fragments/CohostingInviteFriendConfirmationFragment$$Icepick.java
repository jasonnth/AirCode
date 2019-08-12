package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendConfirmationFragment;
import com.airbnb.android.core.models.Listing;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingInviteFriendConfirmationFragment$$Icepick<T extends CohostingInviteFriendConfirmationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7807H = new Helper("com.airbnb.android.cohosting.fragments.CohostingInviteFriendConfirmationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7807H.getParcelable(state, "listing");
            target.listingManagers = f7807H.getParcelableArrayList(state, "listingManagers");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7807H.putParcelable(state, "listing", target.listing);
        f7807H.putParcelableArrayList(state, "listingManagers", target.listingManagers);
    }
}
