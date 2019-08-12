package com.airbnb.android.booking.activities;

import android.os.Bundle;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.booking.activities.CreditCardActivity.Flow;
import com.airbnb.android.core.activities.SheetFlowActivity$$Icepick;
import com.airbnb.android.core.models.payments.CreditCardDetails;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class CreditCardActivity$$Icepick<T extends CreditCardActivity> extends SheetFlowActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7419H = new Helper("com.airbnb.android.booking.activities.CreditCardActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.creditCardDetails = (CreditCardDetails) f7419H.getParcelable(state, "creditCardDetails");
            target.analyticsData = (ParcelStrap) f7419H.getParcelable(state, "analyticsData");
            target.flow = (Flow) f7419H.getSerializable(state, ManageListingAnalytics.FLOW);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7419H.putParcelable(state, "creditCardDetails", target.creditCardDetails);
        f7419H.putParcelable(state, "analyticsData", target.analyticsData);
        f7419H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
    }
}
