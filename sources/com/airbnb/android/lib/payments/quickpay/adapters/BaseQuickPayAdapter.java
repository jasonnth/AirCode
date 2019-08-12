package com.airbnb.android.lib.payments.quickpay.adapters;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;

public abstract class BaseQuickPayAdapter extends AirEpoxyAdapter {
    public abstract void setPaymentOption(PaymentOption paymentOption);

    public abstract void setPriceQuote(BillPriceQuote billPriceQuote);

    public abstract void toggleLoadingState(boolean z);

    public BaseQuickPayAdapter(boolean autoDividerEnabled) {
        super(autoDividerEnabled);
    }

    public void updateLegalAndFxRow(BillPriceQuote billPriceQuote) {
    }
}
