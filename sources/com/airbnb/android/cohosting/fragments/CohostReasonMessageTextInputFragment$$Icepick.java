package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.models.ListingManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostReasonMessageTextInputFragment$$Icepick<T extends CohostReasonMessageTextInputFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7803H = new Helper("com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.type = (CohostReasonSelectionType) f7803H.getSerializable(state, "type");
            target.manager = (ListingManager) f7803H.getParcelable(state, "manager");
            target.listingId = f7803H.getLong(state, "listingId");
            target.privateFeedback = f7803H.getString(state, "privateFeedback");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7803H.putSerializable(state, "type", target.type);
        f7803H.putParcelable(state, "manager", target.manager);
        f7803H.putLong(state, "listingId", target.listingId);
        f7803H.putString(state, "privateFeedback", target.privateFeedback);
    }
}
