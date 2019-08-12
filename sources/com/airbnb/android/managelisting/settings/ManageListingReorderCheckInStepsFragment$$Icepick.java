package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingReorderCheckInStepsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageListingReorderCheckInStepsFragment$$Icepick<T extends ManageListingReorderCheckInStepsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10183H = new Helper("com.airbnb.android.managelisting.settings.ManageListingReorderCheckInStepsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentStepOrdering = (ArrayList) f10183H.getSerializable(state, "currentStepOrdering");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10183H.putSerializable(state, "currentStepOrdering", target.currentStepOrdering);
    }
}
