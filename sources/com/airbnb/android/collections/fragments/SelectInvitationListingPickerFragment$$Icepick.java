package com.airbnb.android.collections.fragments;

import android.os.Bundle;
import com.airbnb.android.collections.fragments.SelectInvitationListingPickerFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectInvitationListingPickerFragment$$Icepick<T extends SelectInvitationListingPickerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7928H = new Helper("com.airbnb.android.collections.fragments.SelectInvitationListingPickerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listings = f7928H.getParcelableArrayList(state, "listings");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7928H.putParcelableArrayList(state, "listings", target.listings);
    }
}
