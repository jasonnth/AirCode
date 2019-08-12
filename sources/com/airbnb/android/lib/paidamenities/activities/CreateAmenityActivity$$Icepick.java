package com.airbnb.android.lib.paidamenities.activities;

import android.os.Bundle;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity;
import com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity.Flow;
import com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreateAmenityActivity$$Icepick<T extends CreateAmenityActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9603H = new Helper("com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paidAmenityDetails = (PaidAmenityDetails) f9603H.getParcelable(state, "paidAmenityDetails");
            target.flow = (Flow) f9603H.getSerializable(state, ManageListingAnalytics.FLOW);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9603H.putParcelable(state, "paidAmenityDetails", target.paidAmenityDetails);
        f9603H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
    }
}
