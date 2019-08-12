package com.airbnb.android.lib.payments.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.fragments.CreditCardDetailsFragment;
import icepick.State;

public class CreditCardDetailsActivity extends AirActivity {
    public static final String EXTRA_COUNTRY_CODE = "extra_country_code";
    public static final String EXTRA_PAYMENT_METHOD_TYPE = "extra_payment_method_type";
    @State
    String countryCode;
    @State
    PaymentMethodType paymentMethodType;

    public static Intent intentForAddCreditCard(Context context, PaymentMethodType paymentMethodType2, String countryCode2) {
        return new Intent(context, CreditCardDetailsActivity.class).putExtra(EXTRA_PAYMENT_METHOD_TYPE, paymentMethodType2).putExtra("extra_country_code", countryCode2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.paymentMethodType = (PaymentMethodType) getIntent().getSerializableExtra(EXTRA_PAYMENT_METHOD_TYPE);
            this.countryCode = getIntent().getStringExtra("extra_country_code");
            showFragment(CreditCardDetailsFragment.newInstance(this.paymentMethodType, this.countryCode), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }
}
