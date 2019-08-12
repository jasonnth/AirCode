package com.airbnb.android.lib.payments.creditcard.brazil.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.lib.payments.creditcard.brazil.fragments.BrazilCreditCardDetailsFragment;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BrazilCreditCardDetailsFragment$$Icepick<T extends BrazilCreditCardDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9635H = new Helper("com.airbnb.android.lib.payments.creditcard.brazil.fragments.BrazilCreditCardDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedBirthday = (AirDate) f9635H.getParcelable(state, "selectedBirthday");
            target.brazilCep = (BrazilCep) f9635H.getParcelable(state, "brazilCep");
            target.creditCard = (DigitalRiverCreditCard) f9635H.getSerializable(state, "creditCard");
            target.firstName = f9635H.getString(state, "firstName");
            target.lastName = f9635H.getString(state, "lastName");
            target.mobileNumber = f9635H.getString(state, "mobileNumber");
            target.csePayload = f9635H.getString(state, "csePayload");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9635H.putParcelable(state, "selectedBirthday", target.selectedBirthday);
        f9635H.putParcelable(state, "brazilCep", target.brazilCep);
        f9635H.putSerializable(state, "creditCard", target.creditCard);
        f9635H.putString(state, "firstName", target.firstName);
        f9635H.putString(state, "lastName", target.lastName);
        f9635H.putString(state, "mobileNumber", target.mobileNumber);
        f9635H.putString(state, "csePayload", target.csePayload);
    }
}
