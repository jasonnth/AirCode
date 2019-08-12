package com.airbnb.android.registration;

import android.os.Bundle;
import com.airbnb.android.registration.EmailRegistrationFragment;
import com.airbnb.p027n2.collections.SheetState;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EmailRegistrationFragment$$Icepick<T extends EmailRegistrationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1565H = new Helper("com.airbnb.android.registration.EmailRegistrationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f1565H.getString(state, "email");
            target.sheetState = (SheetState) f1565H.getSerializable(state, "sheetState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1565H.putString(state, "email", target.email);
        f1565H.putSerializable(state, "sheetState", target.sheetState);
    }
}
