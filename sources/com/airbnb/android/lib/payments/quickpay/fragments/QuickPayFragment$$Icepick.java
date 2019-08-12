package com.airbnb.android.lib.payments.quickpay.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.payments.quickpay.fragments.QuickPayFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class QuickPayFragment$$Icepick<T extends QuickPayFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9649H = new Helper("com.airbnb.android.lib.payments.quickpay.fragments.QuickPayFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentOptions = f9649H.getParcelableArrayList(state, "paymentOptions");
            target.selectedPaymentOption = (PaymentOption) f9649H.getParcelable(state, "selectedPaymentOption");
            target.clientType = (QuickPayClientType) f9649H.getSerializable(state, "clientType");
            target.billProductType = (BillProductType) f9649H.getSerializable(state, "billProductType");
            target.billPriceQuote = (BillPriceQuote) f9649H.getParcelable(state, "billPriceQuote");
            target.cartItem = (CartItem) f9649H.getParcelable(state, "cartItem");
            target.bill = (Bill) f9649H.getParcelable(state, "bill");
            target.postalCode = f9649H.getString(state, PostalAddress.POSTAL_CODE_KEY);
            target.settlementCurrency = f9649H.getString(state, "settlementCurrency");
            target.shouldIncludeAirbnbCredit = f9649H.getBoolean(state, "shouldIncludeAirbnbCredit");
            target.isFirstTimePriceQuote = f9649H.getBoolean(state, "isFirstTimePriceQuote");
            target.userAgreedToCurrencyMismatch = f9649H.getBoolean(state, "userAgreedToCurrencyMismatch");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9649H.putParcelableArrayList(state, "paymentOptions", target.paymentOptions);
        f9649H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9649H.putSerializable(state, "clientType", target.clientType);
        f9649H.putSerializable(state, "billProductType", target.billProductType);
        f9649H.putParcelable(state, "billPriceQuote", target.billPriceQuote);
        f9649H.putParcelable(state, "cartItem", target.cartItem);
        f9649H.putParcelable(state, "bill", target.bill);
        f9649H.putString(state, PostalAddress.POSTAL_CODE_KEY, target.postalCode);
        f9649H.putString(state, "settlementCurrency", target.settlementCurrency);
        f9649H.putBoolean(state, "shouldIncludeAirbnbCredit", target.shouldIncludeAirbnbCredit);
        f9649H.putBoolean(state, "isFirstTimePriceQuote", target.isFirstTimePriceQuote);
        f9649H.putBoolean(state, "userAgreedToCurrencyMismatch", target.userAgreedToCurrencyMismatch);
    }
}
