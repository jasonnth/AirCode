package com.airbnb.android.cohosting.activities;

import android.os.Bundle;
import com.airbnb.android.cohosting.activities.CohostReasonSelectionActivity;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.intents.CohostingIntents.CohostReasonType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostReasonSelectionActivity$$Icepick<T extends CohostReasonSelectionActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7788H = new Helper("com.airbnb.android.cohosting.activities.CohostReasonSelectionActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.manager = (ListingManager) f7788H.getParcelable(state, "manager");
            target.listing = (Listing) f7788H.getParcelable(state, "listing");
            target.cohostReasonType = (CohostReasonType) f7788H.getSerializable(state, "cohostReasonType");
            target.cohostReasonSelectionType = (CohostReasonSelectionType) f7788H.getSerializable(state, "cohostReasonSelectionType");
            target.privateFeedback = f7788H.getString(state, "privateFeedback");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7788H.putParcelable(state, "manager", target.manager);
        f7788H.putParcelable(state, "listing", target.listing);
        f7788H.putSerializable(state, "cohostReasonType", target.cohostReasonType);
        f7788H.putSerializable(state, "cohostReasonSelectionType", target.cohostReasonSelectionType);
        f7788H.putString(state, "privateFeedback", target.privateFeedback);
    }
}
