package com.airbnb.android.lib.payments.networking.billpricequote;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams;

public interface BillPriceQuoteApi {
    void fetchBillPriceQuote(QuickPayClientType quickPayClientType, PaymentOption paymentOption, QuickPayParameters quickPayParameters, boolean z, String str);

    void fetchBillPriceQuoteV2(BillPriceQuoteRequestParams billPriceQuoteRequestParams);
}
