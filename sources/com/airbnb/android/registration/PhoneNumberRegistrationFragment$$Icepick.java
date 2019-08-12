package com.airbnb.android.registration;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.registration.PhoneNumberRegistrationFragment;
import com.airbnb.p027n2.collections.SheetState;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberRegistrationFragment$$Icepick<T extends PhoneNumberRegistrationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1567H = new Helper("com.airbnb.android.registration.PhoneNumberRegistrationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCodeItem = (CountryCodeItem) f1567H.getParcelable(state, "countryCodeItem");
            target.airPhone = (AirPhone) f1567H.getParcelable(state, "airPhone");
            target.sheetState = (SheetState) f1567H.getSerializable(state, "sheetState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1567H.putParcelable(state, "countryCodeItem", target.countryCodeItem);
        f1567H.putParcelable(state, "airPhone", target.airPhone);
        f1567H.putSerializable(state, "sheetState", target.sheetState);
    }
}
