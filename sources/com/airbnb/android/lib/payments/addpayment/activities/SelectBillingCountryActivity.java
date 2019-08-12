package com.airbnb.android.lib.payments.addpayment.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment;
import com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment.CountrySelectedListener;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class SelectBillingCountryActivity extends AirActivity implements CountrySelectedListener {
    private static final String EXTRA_COUNTRY_CODE = "extra_country_code";
    private static final String EXTRA_PRODUCT_TYPE = "extra_product_type";
    public static final String RESULT_EXTRA_COUNTRY_CODE = "result_extra_country_code";
    public static final String RESULT_EXTRA_PAYMENT_OPTIONS = "result_extra_payment_options";
    @State
    BillProductType billProductType;
    @State
    String countryCode;

    public static Intent intentForCountryCode(Context context, BillProductType billProductType2, String countryCode2) {
        return new Intent(context, SelectBillingCountryActivity.class).putExtra(EXTRA_PRODUCT_TYPE, billProductType2).putExtra("extra_country_code", countryCode2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.billProductType = (BillProductType) getIntent().getSerializableExtra(EXTRA_PRODUCT_TYPE);
            this.countryCode = getIntent().getStringExtra("extra_country_code");
            showFragment(SelectBillingCountryFragment.newInstance(this.billProductType, this.countryCode), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    public void onBillingCountryUpdated(List<PaymentOption> paymentOptions, String countryCode2) {
        if (countryCode2 != null) {
            Intent intent = new Intent();
            intent.putExtra(RESULT_EXTRA_COUNTRY_CODE, countryCode2);
            intent.putExtra("result_extra_payment_options", new ArrayList(paymentOptions));
            setResult(-1, intent);
        }
        finish();
    }
}
