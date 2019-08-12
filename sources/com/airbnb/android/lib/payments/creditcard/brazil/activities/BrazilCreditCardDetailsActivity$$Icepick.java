package com.airbnb.android.lib.payments.creditcard.brazil.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.lib.payments.creditcard.brazil.activities.BrazilCreditCardDetailsActivity;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BrazilCreditCardDetailsActivity$$Icepick<T extends BrazilCreditCardDetailsActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9634H = new Helper("com.airbnb.android.lib.payments.creditcard.brazil.activities.BrazilCreditCardDetailsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.brazilCep = (BrazilCep) f9634H.getParcelable(state, "brazilCep");
            target.creditCard = (DigitalRiverCreditCard) f9634H.getSerializable(state, "creditCard");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9634H.putParcelable(state, "brazilCep", target.brazilCep);
        f9634H.putSerializable(state, "creditCard", target.creditCard);
    }
}
