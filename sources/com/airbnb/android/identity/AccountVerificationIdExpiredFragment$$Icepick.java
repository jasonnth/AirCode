package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.AccountVerificationIdExpiredFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationIdExpiredFragment$$Icepick<T extends AccountVerificationIdExpiredFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9165H = new Helper("com.airbnb.android.identity.AccountVerificationIdExpiredFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.governmentIdType = (GovernmentIdType) f9165H.getSerializable(state, "governmentIdType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9165H.putSerializable(state, "governmentIdType", target.governmentIdType);
    }
}
