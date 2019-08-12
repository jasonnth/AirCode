package com.airbnb.android.lib.payments.networking.billpricequote;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.PriceQuote;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams;
import p032rx.Observer;

public class BillPriceQuoteDelegate implements BillPriceQuoteApi {
    private final BillPriceQuoteDelegateListener billPriceQuoteDelegateListener;
    private final BillPriceQuoteRequestFactory billPriceQuoteRequestFactory;
    final RequestListener<PriceQuote> billPriceQuoteRequestListener = new C0699RL().onResponse(BillPriceQuoteDelegate$$Lambda$1.lambdaFactory$(this)).onError(BillPriceQuoteDelegate$$Lambda$2.lambdaFactory$(this)).build();
    private final RequestManager requestManager;

    public interface BillPriceQuoteDelegateListener {
        void onBillPriceQuoteRequestError(NetworkException networkException);

        void onBillPriceQuoteRequestSuccess(BillPriceQuote billPriceQuote);
    }

    public BillPriceQuoteDelegate(RequestManager requestManager2, BillPriceQuoteDelegateListener billPriceQuoteDelegateListener2, BillPriceQuoteRequestFactory billPriceQuoteRequestFactory2) {
        this.requestManager = requestManager2;
        this.billPriceQuoteDelegateListener = billPriceQuoteDelegateListener2;
        this.billPriceQuoteRequestFactory = billPriceQuoteRequestFactory2;
        this.requestManager.subscribe(this);
    }

    public void fetchBillPriceQuote(QuickPayClientType clientType, PaymentOption paymentOption, QuickPayParameters quickPayParameters, boolean shouldIncludeAirbnbCredit, String displayCurrency) {
        this.billPriceQuoteRequestFactory.createBillPriceQuoteRequest(clientType, paymentOption, quickPayParameters, shouldIncludeAirbnbCredit, displayCurrency).withListener((Observer) this.billPriceQuoteRequestListener).execute(this.requestManager);
    }

    public void fetchBillPriceQuoteV2(BillPriceQuoteRequestParams requestParams) {
        this.billPriceQuoteRequestFactory.createBillPriceQuoteRequestV2(requestParams).withListener((Observer) this.billPriceQuoteRequestListener).execute(this.requestManager);
    }

    private BillPriceQuoteDelegateListener getBillPriceQuoteDelegateListener() {
        return this.billPriceQuoteDelegateListener;
    }
}
