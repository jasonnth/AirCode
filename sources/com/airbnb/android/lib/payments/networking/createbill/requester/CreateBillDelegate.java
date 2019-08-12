package com.airbnb.android.lib.payments.networking.createbill.requester;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.security.ThreatMetrixClient;
import com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters;
import com.airbnb.android.lib.payments.networking.requests.CreateBillRequest;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBody;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV1;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV1.Builder;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV2;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.airbnb.android.lib.payments.networking.responses.CreateBillResponse;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.ProfilingResult;
import p032rx.Observer;

public class CreateBillDelegate implements CreateBillApi {
    private final CreateBillDelegateListener delegateListener;
    final RequestListener<CreateBillResponse> requestListener = new C0699RL().onResponse(CreateBillDelegate$$Lambda$1.lambdaFactory$(this)).onError(CreateBillDelegate$$Lambda$2.lambdaFactory$(this)).build();
    private final RequestManager requestManager;
    private final ThreatMetrixClient threatMetrixClient;

    public interface CreateBillDelegateListener {
        void onCreateBillFailure(NetworkException networkException);

        void onCreateBillSuccess(Bill bill);
    }

    public CreateBillDelegate(RequestManager requestManager2, CreateBillDelegateListener delegateListener2, ThreatMetrixClient threatMetrixClient2) {
        this.requestManager = requestManager2;
        this.delegateListener = delegateListener2;
        this.threatMetrixClient = threatMetrixClient2;
        requestManager2.subscribe(this);
    }

    public void createBill(CreateBillParameters parameters) {
        doThreatMetrixProfile(parameters.billPriceQuote(), parameters.selectedPaymentOption(), CreateBillDelegate$$Lambda$3.lambdaFactory$(this, parameters));
    }

    static /* synthetic */ void lambda$createBill$0(CreateBillDelegate createBillDelegate, CreateBillParameters parameters, ProfilingResult profilingResult) {
        CreateBillRequestBody createBillRequestBody;
        switch (parameters.billProductType()) {
            case GiftCredit:
            case PaidAmenity:
                createBillRequestBody = createBillDelegate.createBillRequestBodyV1(parameters, profilingResult.getSessionID());
                break;
            case ResyReservation:
                createBillRequestBody = createBillDelegate.createBillRequestBodyResy(parameters);
                break;
            case Homes:
                createBillRequestBody = createBillDelegate.createBillRequestBodyHomes(parameters);
                break;
            case Trip:
                createBillRequestBody = createBillDelegate.createBillRequestBodyTrips(parameters);
                break;
            default:
                throw new IllegalArgumentException("You must pass in a valid BillProductType");
        }
        CreateBillRequest.forBody(createBillRequestBody).withListener((Observer) createBillDelegate.requestListener).execute(createBillDelegate.requestManager);
        createBillDelegate.threatMetrixClient.doPackageScan(0);
    }

    private CreateBillRequestBody createBillRequestBodyV1(CreateBillParameters params, String threatMetrixSessionId) {
        boolean z = false;
        Builder idempotenceKey = CreateBillRequestBodyV1.make().userId(params.userId()).paymentOption(params.selectedPaymentOption()).displayCurrency(params.currency()).quickPayParameters(params.quickPayParameters()).billPriceQuoteKey(params.billPriceQuote().getQuoteKey()).idempotenceKey(params.billPriceQuote().getQuoteKey());
        if (params.shouldIncludeAirbnbCredit() != null) {
            z = params.shouldIncludeAirbnbCredit().booleanValue();
        }
        return idempotenceKey.isAirbnbCreditIncluded(z).threatMetrixSessionId(threatMetrixSessionId).zipRetry(params.postalCode()).cvvNonce(params.cvvNonce()).build();
    }

    private CreateBillRequestBody createBillRequestBodyResy(CreateBillParameters params) {
        return CreateBillRequestBodyV2.builder().billPriceQuoteKey(params.billPriceQuote().getQuoteKey()).idempodenceKey(params.billPriceQuote().getQuoteKey()).paymentParam(PaymentParam.builder().displayCurrency(params.currency()).build()).userId(params.userId()).build();
    }

    private CreateBillRequestBody createBillRequestBodyHomes(CreateBillParameters params) {
        return CreateBillRequestBodyV2.builder().billPriceQuoteKey(params.billPriceQuote().getQuoteKey()).idempodenceKey(params.billPriceQuote().getQuoteKey()).userId(params.userId())._format(CreateBillRequestBodyV2.API_FORMAT)._intents(CreateBillRequestBodyV2.API_INTENT).paymentParam(createPaymentParam(params.selectedPaymentOption(), params.shouldIncludeAirbnbCredit().booleanValue(), params.currency())).build();
    }

    private CreateBillRequestBody createBillRequestBodyTrips(CreateBillParameters params) {
        return CreateBillRequestBodyV2.builder().billPriceQuoteKey(params.billPriceQuote().getQuoteKey()).idempodenceKey(params.billPriceQuote().getQuoteKey()).userId(params.userId())._format(CreateBillRequestBodyV2.API_TRIPS_FORMAT)._intents(CreateBillRequestBodyV2.API_INTENT).paymentParam(createPaymentParam(params.selectedPaymentOption(), params.shouldIncludeAirbnbCredit().booleanValue(), params.currency())).build();
    }

    private PaymentParam createPaymentParam(PaymentOption paymentOption, boolean shouldIncludeAirbnbCredit, String displayCurrency) {
        if (shouldExcludePaymentParam(paymentOption)) {
            return null;
        }
        return defaultPaymentParams(paymentOption, shouldIncludeAirbnbCredit, displayCurrency);
    }

    private PaymentParam defaultPaymentParams(PaymentOption paymentOption, boolean shouldIncludeAirbnbCredit, String displayCurrency) {
        return PaymentParam.builder().gibraltarInstrumentId(Long.valueOf(paymentOption.getGibraltarInstrumentId())).method(paymentOption.getPaymentMethodType()).airbnbCredit(shouldIncludeAirbnbCredit).displayCurrency(displayCurrency).build();
    }

    private boolean shouldExcludePaymentParam(PaymentOption paymentOption) {
        PaymentMethodType paymentMethodType = PaymentMethodType.findByServerKey(paymentOption.getPaymentMethodType());
        return paymentMethodType == PaymentMethodType.AmexExpressCheckout || paymentMethodType == PaymentMethodType.CreditCard || paymentMethodType == PaymentMethodType.DigitalRiverCreditCard || paymentMethodType == PaymentMethodType.PayPal || Long.valueOf(paymentOption.getGibraltarInstrumentId()).longValue() == 0;
    }

    private String getInstrumentIdForThreatMetrix(BillPriceQuote billPriceQuote, PaymentOption paymentOption) {
        if (isFreePurchase(billPriceQuote)) {
            return null;
        }
        return String.valueOf(paymentOption.getLegacyInstrumentId());
    }

    private boolean isFreePurchase(BillPriceQuote billPriceQuote) {
        return billPriceQuote != null && billPriceQuote.getPrice().getTotal().isZero();
    }

    private void doThreatMetrixProfile(BillPriceQuote billPriceQuote, PaymentOption paymentOption, EndNotifier endNotifier) {
        this.threatMetrixClient.doProfile(new ProfilingOptions().setCustomAttributes(this.threatMetrixClient.customAttributes(getInstrumentIdForThreatMetrix(billPriceQuote, paymentOption), String.valueOf(billPriceQuote.getPrice().getTotal().getAmount()), billPriceQuote.getPrice().getTotal().getCurrency())).setEndNotifier(endNotifier));
    }

    private CreateBillDelegateListener getDelegateListener() {
        return this.delegateListener;
    }
}
