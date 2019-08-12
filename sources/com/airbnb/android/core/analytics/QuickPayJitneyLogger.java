package com.airbnb.android.core.analytics;

import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.enums.PaymentMethod;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.responses.QuickPayErrorResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.jitney.event.logging.PaymentInstrumentRowSection.p182v1.C2503PaymentInstrumentRowSection;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsAlipayIdEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsAlipayNationalIdEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsAlipayPhoneNumberEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsAlipayVerificationSubmitEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsPaymentCcCvvEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsPaymentCcExpirationEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsPaymentCcNumberEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsPaymentCcZipEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsPaymentCountryEvent;
import com.airbnb.jitney.event.logging.Payments.p184v1.PaymentsSelectPaymentMethodEvent;
import com.airbnb.jitney.event.logging.Payments.p185v2.PaymentsConfirmAndPaySuccessEvent;
import com.airbnb.jitney.event.logging.Payments.p185v2.PaymentsQuickpayCreditCardFlowEvent;
import com.airbnb.jitney.event.logging.Payments.p185v2.PaymentsQuickpayPaymentInstrumentRowEvent;
import com.airbnb.jitney.event.logging.Payments.p185v2.PaymentsQuickpayWalletEvent;
import com.airbnb.jitney.event.logging.Payments.p186v3.PaymentsConfirmAndPayErrorEvent;
import com.airbnb.jitney.event.logging.Payments.p188v5.PaymentsQuickpayImpressionEvent.Builder;
import com.airbnb.jitney.event.logging.PaymentsContext.p189v1.C2543PaymentsContext;
import com.airbnb.jitney.event.logging.QuickpayAddCcSection.p215v1.C2596QuickpayAddCcSection;
import com.airbnb.jitney.event.logging.QuickpayConfig.p216v1.C2597QuickpayConfig;
import com.airbnb.jitney.event.logging.QuickpayWalletSection.p218v1.C2599QuickpayWalletSection;

public class QuickPayJitneyLogger extends BaseLogger {
    public static final String DEFAULT_ERROR_KEY_MESSAGE = "missing_error_key";

    public static class QuickPayConfirmAndPayParams {
        public final String localCurrency;
        public final long priceDisplayed;
        public final BillProductType productType;
        public final String reservationCode;
        public final PaymentOption selectedPaymentOption;

        public QuickPayConfirmAndPayParams(PaymentOption selectedPaymentOption2, String localCurrency2, String reservationCode2, long priceDisplayed2, BillProductType productType2) {
            this.selectedPaymentOption = selectedPaymentOption2;
            this.localCurrency = localCurrency2;
            this.reservationCode = reservationCode2;
            this.priceDisplayed = priceDisplayed2;
            this.productType = productType2;
        }
    }

    public QuickPayJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void quickPayImpression(String localCurrency, long priceDisplayed, BillProductType productType) {
        publish(new Builder(context(), paymentsContext(localCurrency, priceDisplayed), convertProductType(productType)));
    }

    public void confirmAndPaySuccess(QuickPayConfirmAndPayParams params) {
        PaymentsConfirmAndPaySuccessEvent.Builder builder = new PaymentsConfirmAndPaySuccessEvent.Builder(context(), paymentsContext(params.localCurrency, Long.valueOf(params.priceDisplayed), params.selectedPaymentOption), convertProductType(params.productType));
        if (params.reservationCode != null) {
            builder.reservation_code(params.reservationCode);
        }
        publish(builder);
    }

    public void confirmAndPayError(QuickPayConfirmAndPayParams params, NetworkException exception) {
        Check.notNull(exception);
        long errorCode = (long) exception.statusCode();
        boolean hasErrorResponse = exception.hasErrorResponse();
        boolean isQuickPayErrorResponse = hasErrorResponse && (((ErrorResponse) exception.errorResponse()) instanceof QuickPayErrorResponse);
        String errorMessage = hasErrorResponse ? ((ErrorResponse) exception.errorResponse()).errorMessage : exception.getMessage();
        String errorDetail = hasErrorResponse ? ((ErrorResponse) exception.errorResponse()).errorDetails : "";
        String errorKey = isQuickPayErrorResponse ? ((QuickPayErrorResponse) exception.errorResponse()).errorKey : DEFAULT_ERROR_KEY_MESSAGE;
        if (errorMessage == null) {
            errorMessage = "";
        }
        if (errorDetail == null) {
            errorDetail = "";
        }
        if (errorKey == null) {
            errorKey = DEFAULT_ERROR_KEY_MESSAGE;
        }
        publish(new PaymentsConfirmAndPayErrorEvent.Builder(context(), paymentsContext(params.localCurrency, Long.valueOf(params.priceDisplayed), params.selectedPaymentOption), convertProductType(params.productType), errorMessage, Long.valueOf(errorCode), errorDetail, errorKey));
    }

    public void paymentCountry(String countryCode) {
        publish(new PaymentsPaymentCountryEvent.Builder(context(), countryCode));
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        publish(new PaymentsSelectPaymentMethodEvent.Builder(context(), paymentMethodToInstrumentType(paymentMethod)));
    }

    public void paymentCcNumber() {
        publish(new PaymentsPaymentCcNumberEvent.Builder(context()));
    }

    public void paymentCcExpiration() {
        publish(new PaymentsPaymentCcExpirationEvent.Builder(context()));
    }

    public void paymentCcCvv() {
        publish(new PaymentsPaymentCcCvvEvent.Builder(context()));
    }

    public void paymentCcZip() {
        publish(new PaymentsPaymentCcZipEvent.Builder(context()));
    }

    public void alipayId() {
        publish(new PaymentsAlipayIdEvent.Builder(context()));
    }

    public void alipayNationalId() {
        publish(new PaymentsAlipayNationalIdEvent.Builder(context()));
    }

    public void alipayPhoneNumber() {
        publish(new PaymentsAlipayPhoneNumberEvent.Builder(context()));
    }

    public void alipayVerificationSubmit() {
        publish(new PaymentsAlipayVerificationSubmitEvent.Builder(context()));
    }

    public void creditCardDetailEntered(C2596QuickpayAddCcSection section) {
        publish(new PaymentsQuickpayCreditCardFlowEvent.Builder(context(), section));
    }

    public void brazilCreditCardFlow(C2596QuickpayAddCcSection section) {
        publish(new PaymentsQuickpayCreditCardFlowEvent.Builder(context(), section));
    }

    public void paymentInstrumentRowClicked(C2503PaymentInstrumentRowSection section, String currency, long priceDisplayed, BillProductType productType) {
        publish(new PaymentsQuickpayPaymentInstrumentRowEvent.Builder(context(), section, paymentsContext(currency, priceDisplayed), convertProductType(productType)));
    }

    public void paymentOptionsEvent(C2599QuickpayWalletSection section, BillProductType productType) {
        paymentOptionsEvent(section, productType, null);
    }

    public void paymentOptionsEvent(C2599QuickpayWalletSection section, BillProductType productType, PaymentOption paymentOption) {
        publish(new PaymentsQuickpayWalletEvent.Builder(context(), section, convertProductType(productType)).payment_instrument_id(paymentOption != null ? Long.valueOf(paymentOption.getGibraltarInstrumentId()) : null).payment_instrument_type(paymentOptionToInstrumentType(paymentOption)));
    }

    private C2597QuickpayConfig convertProductType(BillProductType productType) {
        switch (productType) {
            case Trip:
                return C2597QuickpayConfig.MagicalTrip;
            case PaidAmenity:
                return C2597QuickpayConfig.PaidAmenity;
            case GiftCredit:
                return C2597QuickpayConfig.GiftCredit;
            case ResyReservation:
                return C2597QuickpayConfig.Resy;
            case Homes:
                return C2597QuickpayConfig.Home;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown product type"));
                return null;
        }
    }

    private C2504PaymentInstrumentType paymentMethodToInstrumentType(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CreditCard:
                return C2504PaymentInstrumentType.CreditCard;
            case PayPal:
                return C2504PaymentInstrumentType.Paypal;
            case Alipay:
                return C2504PaymentInstrumentType.Alipay;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown payment method"));
                return null;
        }
    }

    private C2504PaymentInstrumentType paymentOptionToInstrumentType(PaymentOption paymentOption) {
        if (paymentOption == null) {
            return null;
        }
        switch (PaymentMethodType.findByServerKey(paymentOption.getPaymentMethodType())) {
            case CreditCard:
                return C2504PaymentInstrumentType.CreditCard;
            case PayPal:
                return C2504PaymentInstrumentType.Paypal;
            case Alipay:
                return C2504PaymentInstrumentType.Alipay;
            case PayU:
                return C2504PaymentInstrumentType.Payu;
            case BusinessTravelCentralBilling:
            case BusinessTravelInvoice:
                return C2504PaymentInstrumentType.BusinessTravel;
            case AndroidPay:
                return C2504PaymentInstrumentType.AndroidPay;
            case DigitalRiverCreditCard:
                return C2504PaymentInstrumentType.DigitalRiverCreditCard;
            default:
                return null;
        }
    }

    private C2543PaymentsContext paymentsContext(String currency, long priceDisplayed) {
        return new C2543PaymentsContext.Builder(currency, Long.valueOf(priceDisplayed)).build();
    }

    private C2543PaymentsContext paymentsContext(String currency, Long priceDisplayed, PaymentOption paymentOption) {
        C2543PaymentsContext.Builder builder = new C2543PaymentsContext.Builder(currency, priceDisplayed);
        long instrumentId = paymentOption.getLegacyInstrumentId();
        if (instrumentId != 0) {
            builder.payment_instrument_id(Long.valueOf(instrumentId));
        }
        C2504PaymentInstrumentType paymentInstrumentType = paymentOptionToInstrumentType(paymentOption);
        if (paymentInstrumentType != null) {
            builder.payment_instrument_type(paymentInstrumentType);
        }
        return builder.build();
    }
}
