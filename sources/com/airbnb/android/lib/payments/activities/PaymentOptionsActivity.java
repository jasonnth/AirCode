package com.airbnb.android.lib.payments.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment;
import com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment.PaymentOptionsListener;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class PaymentOptionsActivity extends AirActivity implements PaymentOptionsListener {
    private static final String EXTRA_PAYMENT_OPTIONS = "extra_payment_options";
    private static final String EXTRA_QUICK_PAY_CLIENT_TYPE = "extra_client_type";
    private static final String EXTRA_QUICK_PAY_TOTAL_PRICE = "extra_total_price";
    private static final String EXTRA_SELECTED_PAYMENT_OPTION = "extra_selected_payment_option";
    public static final String RESULT_EXTRA_CVV_PAYLOAD = "result_extra_cvv_payload";
    public static final String RESULT_EXTRA_PAYMENT_OPTION = "result_extra_payment_option";
    public static final String RESULT_EXTRA_PAYMENT_OPTIONS = "result_extra_payment_options";
    @State
    QuickPayClientType clientType;
    @State
    ArrayList<PaymentOption> paymentOptions;
    @State
    PaymentOption selectedPaymentOption;
    @State
    Price totalPrice;

    public static Intent intentForPaymentOptionsWithQuickPayClient(Context context, ArrayList<PaymentOption> paymentOptions2, PaymentOption selectedPaymentOption2, QuickPayClientType clientType2, Price totalPrice2) {
        return new Intent(context, PaymentOptionsActivity.class).putParcelableArrayListExtra(EXTRA_PAYMENT_OPTIONS, paymentOptions2).putExtra("extra_selected_payment_option", selectedPaymentOption2).putExtra("extra_client_type", clientType2).putExtra("extra_total_price", totalPrice2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.paymentOptions = getIntent().getParcelableArrayListExtra(EXTRA_PAYMENT_OPTIONS);
            this.selectedPaymentOption = (PaymentOption) getIntent().getParcelableExtra("extra_selected_payment_option");
            this.clientType = (QuickPayClientType) getIntent().getSerializableExtra("extra_client_type");
            this.totalPrice = (Price) getIntent().getParcelableExtra("extra_total_price");
            Check.notNull(this.clientType);
            if (isLaunchedFromGiftCard()) {
                this.paymentOptions = getCreditCardPaymentOptions();
            }
            showFragment(PaymentOptionsFragment.instanceForPaymentOptions(this.paymentOptions, this.selectedPaymentOption, this.clientType, this.totalPrice), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
        }
    }

    public void onPaymentOptionSelected(List<PaymentOption> paymentOptions2, PaymentOption paymentOption, String cvvPayload) {
        Intent data = new Intent();
        data.putParcelableArrayListExtra("result_extra_payment_options", Lists.newArrayList((Iterable<? extends E>) paymentOptions2));
        data.putExtra("result_extra_payment_option", paymentOption);
        data.putExtra("result_extra_cvv_payload", cvvPayload);
        setResult(-1, data);
        finish();
    }

    private boolean isLaunchedFromGiftCard() {
        return this.clientType.equals(QuickPayClientType.GiftCard);
    }

    private ArrayList<PaymentOption> getCreditCardPaymentOptions() {
        return Lists.newArrayList((Iterable<? extends E>) FluentIterable.from((Iterable<E>) this.paymentOptions).filter(PaymentOptionsActivity$$Lambda$1.lambdaFactory$()).toList());
    }
}
