package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.lib.activities.VerificationsLoaderActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class VerificationsLoaderActivity$$Icepick<T extends VerificationsLoaderActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9482H = new Helper("com.airbnb.android.lib.activities.VerificationsLoaderActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.verificationFlow = (VerificationFlow) f9482H.getSerializable(state, "verificationFlow");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9482H.putSerializable(state, "verificationFlow", target.verificationFlow);
    }
}
