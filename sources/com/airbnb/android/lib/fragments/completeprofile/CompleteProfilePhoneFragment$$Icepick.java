package com.airbnb.android.lib.fragments.completeprofile;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CompleteProfilePhoneFragment$$Icepick<T extends CompleteProfilePhoneFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9568H = new Helper("com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isEditPhoneNumber = f9568H.getBoolean(state, "isEditPhoneNumber");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9568H.putBoolean(state, "isEditPhoneNumber", target.isEditPhoneNumber);
    }
}
