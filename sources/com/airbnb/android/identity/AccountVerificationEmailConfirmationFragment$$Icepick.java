package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.AccountVerificationEmailConfirmationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationEmailConfirmationFragment$$Icepick<T extends AccountVerificationEmailConfirmationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9162H = new Helper("com.airbnb.android.identity.AccountVerificationEmailConfirmationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f9162H.getString(state, "email");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9162H.putString(state, "email", target.email);
    }
}
