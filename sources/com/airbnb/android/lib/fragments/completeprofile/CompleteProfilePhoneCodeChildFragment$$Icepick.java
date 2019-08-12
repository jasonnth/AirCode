package com.airbnb.android.lib.fragments.completeprofile;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneCodeChildFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CompleteProfilePhoneCodeChildFragment$$Icepick<T extends CompleteProfilePhoneCodeChildFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9567H = new Helper("com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneCodeChildFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.phoneNumberToEdit = f9567H.getString(state, "phoneNumberToEdit");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9567H.putString(state, "phoneNumberToEdit", target.phoneNumberToEdit);
    }
}
