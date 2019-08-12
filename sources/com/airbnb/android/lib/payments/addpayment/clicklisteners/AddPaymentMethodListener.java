package com.airbnb.android.lib.payments.addpayment.clicklisteners;

import com.airbnb.android.core.payments.models.PaymentMethodType;

public interface AddPaymentMethodListener {
    void onBillingCountryClicked();

    void onPaymentMethodSelected(PaymentMethodType paymentMethodType);
}
