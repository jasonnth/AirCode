package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.IdentityGovIdFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class IdentityGovIdFragment$$Icepick<T extends IdentityGovIdFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9178H = new Helper("com.airbnb.android.identity.IdentityGovIdFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.governmentIdType = (GovernmentIdType) f9178H.getSerializable(state, "governmentIdType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9178H.putSerializable(state, "governmentIdType", target.governmentIdType);
    }
}
