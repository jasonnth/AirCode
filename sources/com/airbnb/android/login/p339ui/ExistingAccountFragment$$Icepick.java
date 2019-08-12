package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.login.p339ui.ExistingAccountFragment;
import com.airbnb.android.login.p339ui.ExistingAccountFragment.ExistingAccountType;
import com.airbnb.android.registration.models.AccountLoginData;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$$Icepick */
public class ExistingAccountFragment$$Icepick<T extends ExistingAccountFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10054H = new Helper("com.airbnb.android.login.ui.ExistingAccountFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.existingAccountData = (AccountLoginData) f10054H.getParcelable(state, "existingAccountData");
            target.existingAccountType = (ExistingAccountType) f10054H.getSerializable(state, "existingAccountType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10054H.putParcelable(state, "existingAccountData", target.existingAccountData);
        f10054H.putSerializable(state, "existingAccountType", target.existingAccountType);
    }
}
