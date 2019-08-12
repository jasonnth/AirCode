package com.airbnb.android.booking.activities;

import android.os.Bundle;
import com.airbnb.android.booking.activities.AlipayActivity;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AlipayActivity$$Icepick<T extends AlipayActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7416H = new Helper("com.airbnb.android.booking.activities.AlipayActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCode = f7416H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.alipayId = f7416H.getString(state, "alipayId");
            target.nationalId = f7416H.getString(state, "nationalId");
            target.phoneNumber = f7416H.getString(state, "phoneNumber");
            target.gibraltarInstrumentId = f7416H.getLong(state, "gibraltarInstrumentId");
            target.analyticsData = (ParcelStrap) f7416H.getParcelable(state, "analyticsData");
            target.paymentInstrument = (OldPaymentInstrument) f7416H.getSerializable(state, "paymentInstrument");
            target.isQuickPay = f7416H.getBoolean(state, "isQuickPay");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7416H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f7416H.putString(state, "alipayId", target.alipayId);
        f7416H.putString(state, "nationalId", target.nationalId);
        f7416H.putString(state, "phoneNumber", target.phoneNumber);
        f7416H.putLong(state, "gibraltarInstrumentId", target.gibraltarInstrumentId);
        f7416H.putParcelable(state, "analyticsData", target.analyticsData);
        f7416H.putSerializable(state, "paymentInstrument", target.paymentInstrument);
        f7416H.putBoolean(state, "isQuickPay", target.isQuickPay);
    }
}
