package com.airbnb.android.lib.fragments.completeprofile;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneChildFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CompleteProfilePhoneChildFragment$$Icepick<T extends CompleteProfilePhoneChildFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9566H = new Helper("com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneChildFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.formattedPhoneNumber = f9566H.getString(state, "formattedPhoneNumber");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9566H.putString(state, "formattedPhoneNumber", target.formattedPhoneNumber);
    }
}
