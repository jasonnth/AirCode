package com.airbnb.android.lib.payments.addpayment.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.addpayment.fragments.AddPaymentMethodFragment;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class AddPaymentMethodActivity extends AirActivity {
    private static final String EXTRA_PAYMENT_OPTIONS = "extra_payment_options";
    private static final String EXTRA_PRODUCT_TYPE = "extra_product_type";
    @State
    ArrayList<PaymentOption> paymentOptions;
    @State
    BillProductType productType;

    public static Intent intentForAddPayment(Context context) {
        return new Intent(context, AddPaymentMethodActivity.class);
    }

    public static Intent intentForAddPayment(Context context, BillProductType productType2, List<PaymentOption> paymentOptions2) {
        return new Intent(context, AddPaymentMethodActivity.class).putExtra(EXTRA_PRODUCT_TYPE, productType2).putExtra(EXTRA_PAYMENT_OPTIONS, new ArrayList(paymentOptions2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.productType = (BillProductType) getIntent().getSerializableExtra(EXTRA_PRODUCT_TYPE);
            this.paymentOptions = getIntent().getParcelableArrayListExtra(EXTRA_PAYMENT_OPTIONS);
            showFragment(AddPaymentMethodFragment.newInstance(this.productType, this.paymentOptions), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }
}
