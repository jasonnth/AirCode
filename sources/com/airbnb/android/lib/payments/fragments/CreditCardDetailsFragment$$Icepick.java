package com.airbnb.android.lib.payments.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.lib.payments.fragments.CreditCardDetailsFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreditCardDetailsFragment$$Icepick<T extends CreditCardDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9642H = new Helper("com.airbnb.android.lib.payments.fragments.CreditCardDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentInstrument = (OldPaymentInstrument) f9642H.getSerializable(state, "paymentInstrument");
            target.paymentMethodType = (PaymentMethodType) f9642H.getSerializable(state, "paymentMethodType");
            target.braintreeCreditCard = (BraintreeCreditCard) f9642H.getSerializable(state, "braintreeCreditCard");
            target.digitalRiverCreditCard = (DigitalRiverCreditCard) f9642H.getSerializable(state, "digitalRiverCreditCard");
            target.countryCode = f9642H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.cvvPayload = f9642H.getString(state, "cvvPayload");
            target.isCreditCardVaulted = f9642H.getBoolean(state, "isCreditCardVaulted");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9642H.putSerializable(state, "paymentInstrument", target.paymentInstrument);
        f9642H.putSerializable(state, "paymentMethodType", target.paymentMethodType);
        f9642H.putSerializable(state, "braintreeCreditCard", target.braintreeCreditCard);
        f9642H.putSerializable(state, "digitalRiverCreditCard", target.digitalRiverCreditCard);
        f9642H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f9642H.putString(state, "cvvPayload", target.cvvPayload);
        f9642H.putBoolean(state, "isCreditCardVaulted", target.isCreditCardVaulted);
    }
}
