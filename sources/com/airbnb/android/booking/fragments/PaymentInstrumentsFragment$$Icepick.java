package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.PaymentInstrumentsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PaymentInstrumentsFragment$$Icepick<T extends PaymentInstrumentsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7435H = new Helper("com.airbnb.android.booking.fragments.PaymentInstrumentsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.didUpdateAndroidPay = f7435H.getBoolean(state, "didUpdateAndroidPay");
            target.didCancelUpdateAndroidPay = f7435H.getBoolean(state, "didCancelUpdateAndroidPay");
            target.didFailAndroidPay = f7435H.getBoolean(state, "didFailAndroidPay");
            target.showBusinessCentralPay = f7435H.getBoolean(state, "showBusinessCentralPay");
            target.allowAlipayRedirect = f7435H.getBoolean(state, "allowAlipayRedirect");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7435H.putBoolean(state, "didUpdateAndroidPay", target.didUpdateAndroidPay);
        f7435H.putBoolean(state, "didCancelUpdateAndroidPay", target.didCancelUpdateAndroidPay);
        f7435H.putBoolean(state, "didFailAndroidPay", target.didFailAndroidPay);
        f7435H.putBoolean(state, "showBusinessCentralPay", target.showBusinessCentralPay);
        f7435H.putBoolean(state, "allowAlipayRedirect", target.allowAlipayRedirect);
    }
}
