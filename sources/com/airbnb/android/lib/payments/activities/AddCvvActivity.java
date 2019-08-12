package com.airbnb.android.lib.payments.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.fragments.AddCvvFragment;
import com.airbnb.android.lib.payments.fragments.AddCvvFragment.CvvNonceCreatedListener;
import icepick.State;
import java.util.ArrayList;

public class AddCvvActivity extends AirActivity implements CvvNonceCreatedListener {
    public static final String EXTRA_CLIENT_TYPE = "extra_client_type";
    public static final String EXTRA_PAYMENT_OPTIONS = "extra_payment_option";
    public static final String EXTRA_SELECTED_PAYMENT_OPTION = "extra_selected_payment_option";
    public static final String EXTRA_TOTAL_PRICE = "extra_total_price";
    public static final String RESULT_EXTRA_CVV_NONCE = "result_extra_payment_instrument_cvv";
    public static final String RESULT_EXTRA_PAYMENT_OPTION = "result_extra_payment_option";
    @State
    QuickPayClientType clientType;
    @State
    ArrayList<PaymentOption> paymentOption;
    @State
    PaymentOption selectedPaymentOption;
    @State
    Price totalPrice;

    public static Intent intentForSecurityCode(Context context, ArrayList<PaymentOption> paymentOptions, PaymentOption selectedPaymentOption2, QuickPayClientType clientType2, Price totalPrice2) {
        return new Intent(context, AddCvvActivity.class).putExtra(EXTRA_PAYMENT_OPTIONS, paymentOptions).putExtra(EXTRA_SELECTED_PAYMENT_OPTION, selectedPaymentOption2).putExtra(EXTRA_CLIENT_TYPE, clientType2).putExtra(EXTRA_TOTAL_PRICE, totalPrice2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.paymentOption = getIntent().getParcelableArrayListExtra(EXTRA_PAYMENT_OPTIONS);
            this.selectedPaymentOption = (PaymentOption) getIntent().getParcelableExtra(EXTRA_SELECTED_PAYMENT_OPTION);
            this.clientType = (QuickPayClientType) getIntent().getSerializableExtra(EXTRA_CLIENT_TYPE);
            this.totalPrice = (Price) getIntent().getParcelableExtra(EXTRA_TOTAL_PRICE);
            showFragment(AddCvvFragment.newInstance(this.paymentOption, this.selectedPaymentOption, this.clientType, this.totalPrice), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
        }
    }

    public void setTheme(int resid) {
        super.setTheme(C0880R.C0885style.Theme_Airbnb_TransparentActionBarDarkText);
    }

    public void onCvvNonceCreated(PaymentOption selectedPaymentOption2, String cvvNonce) {
        Intent resultData = new Intent();
        resultData.putExtra("result_extra_payment_option", selectedPaymentOption2);
        resultData.putExtra(RESULT_EXTRA_CVV_NONCE, cvvNonce);
        setResult(-1, resultData);
        finish();
    }
}
