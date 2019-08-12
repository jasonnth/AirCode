package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostingServicesIntroFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingServicesIntroFragment$$Icepick<T extends CohostingServicesIntroFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7811H = new Helper("com.airbnb.android.cohosting.fragments.CohostingServicesIntroFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7811H.getParcelable(state, "listing");
            target.listingManagers = f7811H.getParcelableArrayList(state, "listingManagers");
            target.sourceFlow = (C1958CohostingSourceFlow) f7811H.getSerializable(state, "sourceFlow");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7811H.putParcelable(state, "listing", target.listing);
        f7811H.putParcelableArrayList(state, "listingManagers", target.listingManagers);
        f7811H.putSerializable(state, "sourceFlow", target.sourceFlow);
    }
}
