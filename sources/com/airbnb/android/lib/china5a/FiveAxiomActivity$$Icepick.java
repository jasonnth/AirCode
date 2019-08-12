package com.airbnb.android.lib.china5a;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.lib.china5a.FiveAxiomActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class FiveAxiomActivity$$Icepick<T extends FiveAxiomActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9515H = new Helper("com.airbnb.android.lib.china5a.FiveAxiomActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.host = (User) f9515H.getParcelable(state, TripRole.ROLE_KEY_HOST);
            target.flow = (VerificationFlow) f9515H.getSerializable(state, ManageListingAnalytics.FLOW);
            target.steps = f9515H.getParcelableArrayList(state, "steps");
            target.isCompleted = f9515H.getBoolean(state, "isCompleted");
            target.currentStepIndex = f9515H.getInt(state, "currentStepIndex");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9515H.putParcelable(state, TripRole.ROLE_KEY_HOST, target.host);
        f9515H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
        f9515H.putParcelableArrayList(state, "steps", target.steps);
        f9515H.putBoolean(state, "isCompleted", target.isCompleted);
        f9515H.putInt(state, "currentStepIndex", target.currentStepIndex);
    }
}
