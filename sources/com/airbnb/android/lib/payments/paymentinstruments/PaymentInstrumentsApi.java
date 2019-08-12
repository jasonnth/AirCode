package com.airbnb.android.lib.payments.paymentinstruments;

import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;

public interface PaymentInstrumentsApi {
    void createPaymentInstrument(CreatePaymentInstrumentRequest createPaymentInstrumentRequest);
}
