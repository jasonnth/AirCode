package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.lib.businesstravel.BusinessTravelWelcomeFragment;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BusinessTravelWelcomeFragment$$Icepick<T extends BusinessTravelWelcomeFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9495H = new Helper("com.airbnb.android.lib.businesstravel.BusinessTravelWelcomeFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.welcomeContent = (BusinessTravelWelcomeData) f9495H.getParcelable(state, "welcomeContent");
            target.workEmail = f9495H.getString(state, "workEmail");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9495H.putParcelable(state, "welcomeContent", target.welcomeContent);
        f9495H.putString(state, "workEmail", target.workEmail);
    }
}
