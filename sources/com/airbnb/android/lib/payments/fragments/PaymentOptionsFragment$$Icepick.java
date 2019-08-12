package com.airbnb.android.lib.payments.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PaymentOptionsFragment$$Icepick<T extends PaymentOptionsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9643H = new Helper("com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentOptions = f9643H.getParcelableArrayList(state, "paymentOptions");
            target.selectedPaymentOption = (PaymentOption) f9643H.getParcelable(state, "selectedPaymentOption");
            target.clientType = (QuickPayClientType) f9643H.getSerializable(state, "clientType");
            target.googleMaskedWallet = (MaskedWallet) f9643H.getParcelable(state, "googleMaskedWallet");
            target.googleFullWallet = (FullWallet) f9643H.getParcelable(state, "googleFullWallet");
            target.creditCard = (BraintreeCreditCard) f9643H.getSerializable(state, "creditCard");
            target.totalPrice = (Price) f9643H.getParcelable(state, "totalPrice");
            target.didUpdateAndroidPay = f9643H.getBoolean(state, "didUpdateAndroidPay");
            target.didCancelUpdateAndroidPay = f9643H.getBoolean(state, "didCancelUpdateAndroidPay");
            target.didFailAndroidPay = f9643H.getBoolean(state, "didFailAndroidPay");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9643H.putParcelableArrayList(state, "paymentOptions", target.paymentOptions);
        f9643H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9643H.putSerializable(state, "clientType", target.clientType);
        f9643H.putParcelable(state, "googleMaskedWallet", target.googleMaskedWallet);
        f9643H.putParcelable(state, "googleFullWallet", target.googleFullWallet);
        f9643H.putSerializable(state, "creditCard", target.creditCard);
        f9643H.putParcelable(state, "totalPrice", target.totalPrice);
        f9643H.putBoolean(state, "didUpdateAndroidPay", target.didUpdateAndroidPay);
        f9643H.putBoolean(state, "didCancelUpdateAndroidPay", target.didCancelUpdateAndroidPay);
        f9643H.putBoolean(state, "didFailAndroidPay", target.didFailAndroidPay);
    }
}
