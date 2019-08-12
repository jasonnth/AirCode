package com.airbnb.android.lib.fragments.verifications;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.verifications.PhoneVerificationFragment;
import com.braintreepayments.api.models.PostalAddress;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhoneVerificationFragment$$Icepick<T extends PhoneVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9588H = new Helper("com.airbnb.android.lib.fragments.verifications.PhoneVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.phoneNumber = (PhoneNumber) f9588H.getSerializable(state, "phoneNumber");
            target.requestState = f9588H.getInt(state, "requestState");
            target.displayConfirmationCodeScreen = f9588H.getBoolean(state, "displayConfirmationCodeScreen");
            target.countryCode = f9588H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9588H.putSerializable(state, "phoneNumber", target.phoneNumber);
        f9588H.putInt(state, "requestState", target.requestState);
        f9588H.putBoolean(state, "displayConfirmationCodeScreen", target.displayConfirmationCodeScreen);
        f9588H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
    }
}
