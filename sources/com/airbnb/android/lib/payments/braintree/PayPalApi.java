package com.airbnb.android.lib.payments.braintree;

import com.airbnb.android.core.models.payments.PayPalInstrument;
import java.util.List;

public interface PayPalApi {
    void tokenize(List<String> list);

    void vaultPayPalInstrument(PayPalInstrument payPalInstrument);
}
