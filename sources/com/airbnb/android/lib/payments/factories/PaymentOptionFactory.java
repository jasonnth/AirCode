package com.airbnb.android.lib.payments.factories;

import com.airbnb.android.core.businesstravel.models.BusinessEntityGroup;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.payments.models.PaymentMethodType;

public class PaymentOptionFactory {
    public PaymentOption forNewInstrument(OldPaymentInstrument paymentInstrument) {
        PaymentOption paymentOption = new PaymentOption();
        paymentOption.setIsExistingInstrument(true);
        paymentOption.setGibraltarInstrumentId(paymentInstrument.getId());
        paymentOption.setName(paymentInstrument.getName());
        switch (paymentInstrument.getType()) {
            case BraintreeCreditCard:
                BraintreeCreditCard creditCard = (BraintreeCreditCard) paymentInstrument;
                paymentOption.setCreditCardType(creditCard.getCardType().getKey());
                paymentOption.setCreditCardLastFour(creditCard.getLastFourDigits());
                paymentOption.setPaymentMethodType(PaymentMethodType.CreditCard.getServerKey());
                break;
            case DigitalRiverCreditCard:
                DigitalRiverCreditCard digitalRiverCreditCard = (DigitalRiverCreditCard) paymentInstrument;
                paymentOption.setCreditCardType(digitalRiverCreditCard.getCardType().getKey());
                paymentOption.setCreditCardLastFour(digitalRiverCreditCard.getLastFourDigits());
                paymentOption.setPaymentMethodType(PaymentMethodType.DigitalRiverCreditCard.getServerKey());
                break;
            case PayPal:
                paymentOption.setName(((PayPalInstrument) paymentInstrument).getEmail());
                paymentOption.setPaymentMethodType(PaymentMethodType.PayPal.getServerKey());
                break;
            case Alipay:
                paymentOption.setPaymentMethodType(PaymentMethodType.Alipay.getServerKey());
                break;
            case AndroidPay:
                paymentOption.setPaymentMethodType(PaymentMethodType.AndroidPay.getServerKey());
                break;
            case PayU:
                paymentOption.setPaymentMethodType(PaymentMethodType.PayU.getServerKey());
                break;
            case Sofort:
                paymentOption.setPaymentMethodType(PaymentMethodType.Sofort.getServerKey());
                break;
            case iDEAL:
                paymentOption.setPaymentMethodType(PaymentMethodType.iDEAL.getServerKey());
                break;
        }
        return paymentOption;
    }

    public PaymentOption createDummyAndroidPayPaymentOption() {
        PaymentOption option = new PaymentOption();
        option.setPaymentMethodType(PaymentMethodType.AndroidPay.getServerKey());
        option.setIsExistingInstrument(true);
        return option;
    }

    public PaymentOption createVaultedPaymentOption(PaymentMethodType paymentMethodType) {
        PaymentOption option = new PaymentOption();
        option.setPaymentMethodType(paymentMethodType.getServerKey());
        option.setIsExistingInstrument(true);
        return option;
    }

    public PaymentOption createSkeletonPaymentOption(PaymentMethodType paymentMethodType) {
        PaymentOption option = new PaymentOption();
        option.setPaymentMethodType(paymentMethodType.getServerKey());
        option.setIsExistingInstrument(false);
        return option;
    }

    public PaymentOption createBusinessPaymentOption(PaymentMethodType paymentMethodType, BusinessEntityGroup businessEntityGroup) {
        PaymentOption option = new PaymentOption();
        option.setPaymentMethodType(paymentMethodType.getServerKey());
        option.setBusinessEntityGroup(businessEntityGroup);
        return option;
    }
}
