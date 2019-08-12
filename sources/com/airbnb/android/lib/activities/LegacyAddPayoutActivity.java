package com.airbnb.android.lib.activities;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.data.AddressParts;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.airbnb.android.core.requests.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.PayoutAchPreFragment;
import com.airbnb.android.lib.fragments.PayoutPaypalFragment;
import com.airbnb.android.lib.fragments.PayoutWelcomeFragment;
import com.airbnb.android.utils.Strap;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegacyAddPayoutActivity extends SolitAirActivity {
    public static final String AIREVENT_PAYOUTS = "host_payouts";
    private final List<C6200PaymentInstrumentType> acceptablePayoutTypes = Arrays.asList(new C6200PaymentInstrumentType[]{C6200PaymentInstrumentType.ACH, C6200PaymentInstrumentType.PayPal});
    @State
    AddressParts addressParts;
    @State
    String mCountryCode;
    @State
    ArrayList<String> mSupportedCurrencies;

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar(C0880R.string.payouts, new Object[0]);
        if (savedInstanceState == null) {
            this.mCountryCode = getIntent().getStringExtra("extra_country_code");
            showFragment(PayoutWelcomeFragment.withCountryCode(this.mCountryCode), false);
        }
        AirbnbEventLogger.track(AIREVENT_PAYOUTS, Strap.make().mo11639kv("sub_event", "impression"));
    }

    public void showFragment(Fragment f) {
        super.showFragment(f, true);
    }

    public void goToManagePayoutMethods() {
        startActivity(PayoutActivityIntents.forManagePayoutMethods(this));
        finish();
    }

    public boolean isPaymentSupported(C6200PaymentInstrumentType payoutMethod) {
        return this.acceptablePayoutTypes.contains(payoutMethod);
    }

    public void startAddPayment(C6200PaymentInstrumentType paymentInstrumentType, List<String> supportedCurrencies) {
        if (!isPaymentSupported(paymentInstrumentType)) {
            throw new IllegalStateException("check isPaymentSupported() before calling startAddPayment");
        }
        this.mSupportedCurrencies = Lists.newArrayList((Iterable<? extends E>) supportedCurrencies);
        switch (paymentInstrumentType) {
            case ACH:
                showFragment(PayoutAchPreFragment.newInstance());
                AirbnbEventLogger.track(AIREVENT_PAYOUTS, Strap.make().mo11639kv("sub_event", SecurityCheckAnalytics.PAGE_ADD_PAYOUT).mo11639kv("payout_type", "ach"));
                return;
            case PayPal:
                showFragment(PayoutPaypalFragment.newInstance());
                AirbnbEventLogger.track(AIREVENT_PAYOUTS, Strap.make().mo11639kv("sub_event", SecurityCheckAnalytics.PAGE_ADD_PAYOUT).mo11639kv("payout_type", "paypal"));
                return;
            default:
                return;
        }
    }

    public void setCountryCode(String countryCode) {
        this.mCountryCode = countryCode;
    }

    public List<String> getSupportedCurrencies() {
        return this.mSupportedCurrencies;
    }

    public Strap getNewTrustParameters() {
        return CreatePaymentInstrumentRequest.generateTrustStrap(this.addressParts);
    }

    public void setNewTrustParameters(AddressParts addressParts2) {
        this.addressParts = addressParts2;
    }
}
