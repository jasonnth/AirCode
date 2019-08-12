package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.p011p3.P3Fragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3Fragment$$Icepick */
public class P3Fragment$$Icepick<T extends P3Fragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10535H = new Helper("com.airbnb.android.p3.P3Fragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.firstVerificationStep = f10535H.getString(state, "firstVerificationStep");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10535H.putString(state, "firstVerificationStep", target.firstVerificationStep);
    }
}
