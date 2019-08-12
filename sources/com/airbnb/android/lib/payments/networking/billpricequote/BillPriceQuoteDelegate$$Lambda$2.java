package com.airbnb.android.lib.payments.networking.billpricequote;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BillPriceQuoteDelegate$$Lambda$2 implements Action1 {
    private final BillPriceQuoteDelegate arg$1;

    private BillPriceQuoteDelegate$$Lambda$2(BillPriceQuoteDelegate billPriceQuoteDelegate) {
        this.arg$1 = billPriceQuoteDelegate;
    }

    public static Action1 lambdaFactory$(BillPriceQuoteDelegate billPriceQuoteDelegate) {
        return new BillPriceQuoteDelegate$$Lambda$2(billPriceQuoteDelegate);
    }

    public void call(Object obj) {
        this.arg$1.getBillPriceQuoteDelegateListener().onBillPriceQuoteRequestError((AirRequestNetworkException) obj);
    }
}
