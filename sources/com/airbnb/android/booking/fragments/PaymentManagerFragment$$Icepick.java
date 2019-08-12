package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.PaymentManagerFragment;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PaymentManagerFragment$$Icepick<T extends PaymentManagerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7436H = new Helper("com.airbnb.android.booking.fragments.PaymentManagerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentInstrument = (OldPaymentInstrument) f7436H.getSerializable(state, "paymentInstrument");
            target.googleMaskedWallet = (MaskedWallet) f7436H.getParcelable(state, "googleMaskedWallet");
            target.googleFullWallet = (FullWallet) f7436H.getParcelable(state, "googleFullWallet");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7436H.putSerializable(state, "paymentInstrument", target.paymentInstrument);
        f7436H.putParcelable(state, "googleMaskedWallet", target.googleMaskedWallet);
        f7436H.putParcelable(state, "googleFullWallet", target.googleFullWallet);
    }
}
