package com.airbnb.android.registration;

import android.os.Bundle;
import com.airbnb.android.registration.AccountRegistrationActivity;
import com.airbnb.android.registration.models.AccountRegistrationData;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountRegistrationActivity$$Icepick<T extends AccountRegistrationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1562H = new Helper("com.airbnb.android.registration.AccountRegistrationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.accountRegistrationData = (AccountRegistrationData) f1562H.getParcelable(state, "accountRegistrationData");
            target.registrationSteps = (ArrayList) f1562H.getSerializable(state, "registrationSteps");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1562H.putParcelable(state, "accountRegistrationData", target.accountRegistrationData);
        f1562H.putSerializable(state, "registrationSteps", target.registrationSteps);
    }
}
