package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment.VerificationStatus;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class VerifyWorkEmailFragment$$Icepick<T extends VerifyWorkEmailFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9498H = new Helper("com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.workEmail = f9498H.getString(state, "workEmail");
            target.workEmailStatus = (VerificationStatus) f9498H.getSerializable(state, "workEmailStatus");
            target.workEmailLaunchSource = (WorkEmailLaunchSource) f9498H.getSerializable(state, "workEmailLaunchSource");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9498H.putString(state, "workEmail", target.workEmail);
        f9498H.putSerializable(state, "workEmailStatus", target.workEmailStatus);
        f9498H.putSerializable(state, "workEmailLaunchSource", target.workEmailLaunchSource);
    }
}
