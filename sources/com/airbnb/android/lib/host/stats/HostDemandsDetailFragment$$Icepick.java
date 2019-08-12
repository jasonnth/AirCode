package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingDemandDetails;
import com.airbnb.android.lib.host.stats.HostDemandsDetailFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostDemandsDetailFragment$$Icepick<T extends HostDemandsDetailFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9594H = new Helper("com.airbnb.android.lib.host.stats.HostDemandsDetailFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.aggregateDemandDetails = (ListingDemandDetails) f9594H.getParcelable(state, "aggregateDemandDetails");
            target.listingDemandDetails = f9594H.getParcelableArrayList(state, "listingDemandDetails");
            target.selectedListing = (Listing) f9594H.getParcelable(state, "selectedListing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9594H.putParcelable(state, "aggregateDemandDetails", target.aggregateDemandDetails);
        f9594H.putParcelableArrayList(state, "listingDemandDetails", target.listingDemandDetails);
        f9594H.putParcelable(state, "selectedListing", target.selectedListing);
    }
}
