package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.identity.AccountVerificationPhoneNumberConfirmationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationPhoneNumberConfirmationFragment$$Icepick<T extends AccountVerificationPhoneNumberConfirmationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9169H = new Helper("com.airbnb.android.identity.AccountVerificationPhoneNumberConfirmationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.SMSConfirmationCode = f9169H.getString(state, "SMSConfirmationCode");
            target.airPhone = (AirPhone) f9169H.getParcelable(state, "airPhone");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9169H.putString(state, "SMSConfirmationCode", target.SMSConfirmationCode);
        f9169H.putParcelable(state, "airPhone", target.airPhone);
    }
}
