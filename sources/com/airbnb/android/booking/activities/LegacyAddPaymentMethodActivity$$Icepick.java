package com.airbnb.android.booking.activities;

import android.os.Bundle;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity.Flow;
import com.airbnb.android.core.activities.SheetFlowActivity$$Icepick;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class LegacyAddPaymentMethodActivity$$Icepick<T extends LegacyAddPaymentMethodActivity> extends SheetFlowActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7422H = new Helper("com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.braintreeAuthorization = f7422H.getString(state, "braintreeAuthorization");
            target.countryCode = f7422H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.paymentInstrument = (OldPaymentInstrument) f7422H.getSerializable(state, "paymentInstrument");
            target.analyticsData = (ParcelStrap) f7422H.getParcelable(state, "analyticsData");
            target.isGiftCard = f7422H.getBoolean(state, "isGiftCard");
            target.isQuickPay = f7422H.getBoolean(state, "isQuickPay");
            target.flow = (Flow) f7422H.getSerializable(state, ManageListingAnalytics.FLOW);
            target.creditCard = (BraintreeCreditCard) f7422H.getSerializable(state, "creditCard");
            target.hideAlipayDirect = f7422H.getBoolean(state, "hideAlipayDirect");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7422H.putString(state, "braintreeAuthorization", target.braintreeAuthorization);
        f7422H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f7422H.putSerializable(state, "paymentInstrument", target.paymentInstrument);
        f7422H.putParcelable(state, "analyticsData", target.analyticsData);
        f7422H.putBoolean(state, "isGiftCard", target.isGiftCard);
        f7422H.putBoolean(state, "isQuickPay", target.isQuickPay);
        f7422H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
        f7422H.putSerializable(state, "creditCard", target.creditCard);
        f7422H.putBoolean(state, "hideAlipayDirect", target.hideAlipayDirect);
    }
}
