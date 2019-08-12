package com.airbnb.android.payout.create.controllers;

import android.os.Bundle;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController;
import com.airbnb.android.payout.models.BankDepositAccountType;
import com.airbnb.android.payout.models.PayoutInfoForm;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddPayoutMethodDataController$$Icepick<T extends AddPayoutMethodDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10769H = new Helper("com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.bankAccountType = (BankDepositAccountType) f10769H.getSerializable(state, "bankAccountType");
            target.selectedPayoutInfoForm = (PayoutInfoForm) f10769H.getParcelable(state, "selectedPayoutInfoForm");
            target.payoutCountryCode = f10769H.getString(state, "payoutCountryCode");
            target.payoutCurrency = f10769H.getString(state, "payoutCurrency");
            target.payoutInfoForms = f10769H.getParcelableArrayList(state, "payoutInfoForms");
            target.userAddresses = f10769H.getParcelableArrayList(state, "userAddresses");
            target.payoutAddress = (AirAddress) f10769H.getParcelable(state, "payoutAddress");
            target.createdPaymentInstrument = (PaymentInstrument) f10769H.getParcelable(state, "createdPaymentInstrument");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10769H.putSerializable(state, "bankAccountType", target.bankAccountType);
        f10769H.putParcelable(state, "selectedPayoutInfoForm", target.selectedPayoutInfoForm);
        f10769H.putString(state, "payoutCountryCode", target.payoutCountryCode);
        f10769H.putString(state, "payoutCurrency", target.payoutCurrency);
        f10769H.putParcelableArrayList(state, "payoutInfoForms", target.payoutInfoForms);
        f10769H.putParcelableArrayList(state, "userAddresses", target.userAddresses);
        f10769H.putParcelable(state, "payoutAddress", target.payoutAddress);
        f10769H.putParcelable(state, "createdPaymentInstrument", target.createdPaymentInstrument);
    }
}
