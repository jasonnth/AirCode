package com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1;

/* renamed from: com.airbnb.jitney.event.logging.PaymentInstrumentType.v1.PaymentInstrumentType */
public enum C2504PaymentInstrumentType {
    Ach(1),
    Alipay(2),
    AmexCheckout(3),
    AndroidPay(4),
    ApplePay(5),
    BraintreePaypal(6),
    CreditCard(7),
    DigitalRiverBoleto(8),
    DigitalRiverCreditCard(9),
    EmployeeTravelCredit(10),
    Envoy(11),
    GiftCredit(12),
    GoogleWallet(13),
    PayoneerBankTransfer(14),
    PayoneerDebit(15),
    Paypal(16),
    ReferralCredit(17),
    Sepa(18),
    Vacuba(19),
    WesternUnion(20),
    BusinessTravel(21),
    Ideal(22),
    Payu(23),
    Sofort(24),
    AlipayRedirect(25);
    
    public final int value;

    private C2504PaymentInstrumentType(int value2) {
        this.value = value2;
    }
}
