package com.airbnb.android.booking.activities;

import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;

public interface AlipayV2Facade {
    ParcelStrap getAnalyticsData();

    PaymentInstrument getPaymentInstrument();

    void onAuthorizationFail();

    void onAuthorizationStart();

    void onAuthorizationSuccess();

    void onVerificationRetry();

    void onVerificationTimeout();

    void setPaymentInstrument(PaymentInstrument paymentInstrument);
}
