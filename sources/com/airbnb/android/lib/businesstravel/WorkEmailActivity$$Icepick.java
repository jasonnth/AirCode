package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.lib.businesstravel.WorkEmailActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WorkEmailActivity$$Icepick<T extends WorkEmailActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9499H = new Helper("com.airbnb.android.lib.businesstravel.WorkEmailActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.workEmail = f9499H.getString(state, "workEmail");
            target.confirmationCode = f9499H.getString(state, "confirmationCode");
            target.workEmailStatus = (WorkEmailStatus) f9499H.getSerializable(state, "workEmailStatus");
            target.launchSource = (WorkEmailLaunchSource) f9499H.getSerializable(state, "launchSource");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9499H.putString(state, "workEmail", target.workEmail);
        f9499H.putString(state, "confirmationCode", target.confirmationCode);
        f9499H.putSerializable(state, "workEmailStatus", target.workEmailStatus);
        f9499H.putSerializable(state, "launchSource", target.launchSource);
    }
}
