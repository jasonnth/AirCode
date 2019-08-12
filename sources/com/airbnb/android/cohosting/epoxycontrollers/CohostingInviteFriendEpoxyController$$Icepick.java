package com.airbnb.android.cohosting.epoxycontrollers;

import android.os.Bundle;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController;
import com.airbnb.android.core.models.Listing;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class CohostingInviteFriendEpoxyController$$Icepick<T extends CohostingInviteFriendEpoxyController> extends CohostingPaymentsBaseEpoxyController$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7797H = new Helper("com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f7797H.getString(state, "email");
            target.message = f7797H.getString(state, "message");
            target.listing = (Listing) f7797H.getParcelable(state, "listing");
            target.listingManagers = f7797H.getParcelableArrayList(state, "listingManagers");
            target.cohostInvitations = f7797H.getParcelableArrayList(state, "cohostInvitations");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7797H.putString(state, "email", target.email);
        f7797H.putString(state, "message", target.message);
        f7797H.putParcelable(state, "listing", target.listing);
        f7797H.putParcelableArrayList(state, "listingManagers", target.listingManagers);
        f7797H.putParcelableArrayList(state, "cohostInvitations", target.cohostInvitations);
    }
}
