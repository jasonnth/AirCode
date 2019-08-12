package com.airbnb.android.registration;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.registration.PhoneNumberRegistrationConfirmationFragment;
import com.airbnb.p027n2.collections.SheetState;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberRegistrationConfirmationFragment$$Icepick<T extends PhoneNumberRegistrationConfirmationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1566H = new Helper("com.airbnb.android.registration.PhoneNumberRegistrationConfirmationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.airPhone = (AirPhone) f1566H.getParcelable(state, "airPhone");
            target.sheetState = (SheetState) f1566H.getSerializable(state, "sheetState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1566H.putParcelable(state, "airPhone", target.airPhone);
        f1566H.putSerializable(state, "sheetState", target.sheetState);
    }
}
