package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.AccountVerificationDeviceNotSupportedFragment;
import com.airbnb.android.identity.AccountVerificationDeviceNotSupportedFragment.Mode;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationDeviceNotSupportedFragment$$Icepick<T extends AccountVerificationDeviceNotSupportedFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9161H = new Helper("com.airbnb.android.identity.AccountVerificationDeviceNotSupportedFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mode = (Mode) f9161H.getSerializable(state, "mode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9161H.putSerializable(state, "mode", target.mode);
    }
}
