package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.C0716R;
import com.google.common.collect.FluentIterable;

public enum PaymentMethodType {
    Alipay("alipay_direct"),
    AmexExpressCheckout("amex_express_checkout"),
    AndroidPay("android_pay"),
    BusinessTravelCentralBilling("business_travel_centralized_billing"),
    BusinessTravelInvoice("business_travel_invoice"),
    CreditCard("cc"),
    DigitalRiverCreditCard("digital_river_cc"),
    iDEAL("ideal"),
    PayPal("braintree_paypal"),
    PayU("payu"),
    ExistingPaymentInstrument("payment_instrument"),
    Sofort("sofort"),
    Unknown("");
    
    private final String serverKey;

    private PaymentMethodType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public static PaymentMethodType findByServerKey(String serverKey2) {
        return (PaymentMethodType) FluentIterable.m1283of(values()).firstMatch(PaymentMethodType$$Lambda$1.lambdaFactory$(serverKey2)).mo41059or(Unknown);
    }

    public static int getNameResource(PaymentMethodType paymentMethodType) {
        switch (paymentMethodType) {
            case CreditCard:
            case DigitalRiverCreditCard:
                return C0716R.string.p4_payment_option_credit_card;
            case PayPal:
                return C0716R.string.p4_payment_option_paypal;
            case Alipay:
                return C0716R.string.alipay;
            case AndroidPay:
                return C0716R.string.payment_type_android_pay;
            case PayU:
                return C0716R.string.payment_type_credit_or_debit_card;
            case Sofort:
                return C0716R.string.payment_type_sofort;
            case iDEAL:
                return C0716R.string.payment_type_ideal;
            default:
                return -1;
        }
    }
}
