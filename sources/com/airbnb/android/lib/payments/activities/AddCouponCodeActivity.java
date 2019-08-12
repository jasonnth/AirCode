package com.airbnb.android.lib.payments.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment;
import com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment.AddCouponCodeListener;
import icepick.State;

public class AddCouponCodeActivity extends AirActivity implements AddCouponCodeListener {
    public static final String EXTRA_BILL_PRICE_QUOTE = "key_bill_price_quote";
    public static final String EXTRA_CLIENT_TYPE = "key_client_type";
    public static final String EXTRA_COUPON_CODE = "key_coupon_code";
    public static final String EXTRA_PAYMENT_OPTION = "key_payment_option";
    public static final String EXTRA_QUICK_PAY_PARAMETERS = "key_quick_pay_parameters";
    public static final String EXTRA_SHOULD_INCLUDE_AIRBNB_CREDIT = "key_should_include_airbnb_credit";
    public static final String EXTRA_USER_AGREED_TO_CURRENCY_MISMATCH = "key_user_agreed_to_currency_mismatch";
    @State
    QuickPayClientType clientType;
    @State
    boolean isCreditApplied;
    @State
    QuickPayParameters quickPayParameters;
    @State
    PaymentOption selectedPaymentOption;
    @State
    boolean userAgreedToCurrencyMismatch;

    public static Intent intentForAddCouponCode(Context context, QuickPayClientType clientType2, PaymentOption paymentOption, QuickPayParameters quickPayParameters2, boolean includeAirbnbCredit, boolean userAgreedToCurrencyMismatch2) {
        return new Intent(context, AddCouponCodeActivity.class).putExtra(EXTRA_CLIENT_TYPE, clientType2).putExtra(EXTRA_PAYMENT_OPTION, paymentOption).putExtra(EXTRA_QUICK_PAY_PARAMETERS, quickPayParameters2).putExtra(EXTRA_SHOULD_INCLUDE_AIRBNB_CREDIT, includeAirbnbCredit).putExtra(EXTRA_USER_AGREED_TO_CURRENCY_MISMATCH, userAgreedToCurrencyMismatch2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            this.clientType = (QuickPayClientType) intent.getSerializableExtra(EXTRA_CLIENT_TYPE);
            this.selectedPaymentOption = (PaymentOption) intent.getParcelableExtra(EXTRA_PAYMENT_OPTION);
            this.quickPayParameters = (QuickPayParameters) intent.getParcelableExtra(EXTRA_QUICK_PAY_PARAMETERS);
            this.isCreditApplied = intent.getBooleanExtra(EXTRA_SHOULD_INCLUDE_AIRBNB_CREDIT, false);
            this.userAgreedToCurrencyMismatch = intent.getBooleanExtra(EXTRA_USER_AGREED_TO_CURRENCY_MISMATCH, false);
        }
        showFragment(AddCouponCodeFragment.newInstance(this.clientType, this.selectedPaymentOption, this.quickPayParameters, this.isCreditApplied, this.userAgreedToCurrencyMismatch), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void onCouponCodeApplied(String couponCode, BillPriceQuote billPriceQuote) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_COUPON_CODE, couponCode);
        intent.putExtra(EXTRA_BILL_PRICE_QUOTE, billPriceQuote);
        setResult(-1, intent);
        finish();
    }
}
