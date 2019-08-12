package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.lib.C0880R;

public class PaymentImageUtils {
    public static int getPaymentImageRes(PaymentOption paymentOption) {
        PaymentMethodType paymentMethodType = PaymentMethodType.findByServerKey(paymentOption.getPaymentMethodType());
        switch (paymentMethodType) {
            case CreditCard:
            case DigitalRiverCreditCard:
                return getCardImageRes(CardType.getCardType(paymentOption.getCreditCardType()));
            default:
                return getPaymentImageRes(paymentMethodType);
        }
    }

    public static int getPaymentImageRes(PaymentMethodType paymentMethod) {
        switch (paymentMethod) {
            case CreditCard:
            case DigitalRiverCreditCard:
            case PayU:
                return C0880R.C0881drawable.creditcard_default_icon;
            case PayPal:
                return C0880R.C0881drawable.paypal_icon;
            case Sofort:
                return C0880R.C0881drawable.sofort_icon;
            case Alipay:
                return C0880R.C0881drawable.alipay_icon;
            case BusinessTravelCentralBilling:
            case BusinessTravelInvoice:
                return C0880R.C0881drawable.business_travel_icon;
            case AndroidPay:
                return C0880R.C0881drawable.android_pay_icon;
            case AmexExpressCheckout:
                return C0880R.C0881drawable.amex_icon;
            default:
                return 0;
        }
    }

    public static int getCardImageRes(CardType cardType) {
        switch (cardType) {
            case Visa:
                return C0880R.C0881drawable.visa_icon;
            case MC:
                return C0880R.C0881drawable.mastercard_icon;
            case Amex:
                return C0880R.C0881drawable.amex_icon;
            case Discover:
                return C0880R.C0881drawable.discover_icon;
            case JCB15:
            case JCB16:
                return C0880R.C0881drawable.jcb_icon;
            case Aura:
                return C0880R.C0881drawable.aura_card_icon;
            case Diners:
                return C0880R.C0881drawable.diners_club_icon;
            case Elo:
                return C0880R.C0881drawable.elo_card_icon;
            case Hipercard:
                return C0880R.C0881drawable.hipercard_icon;
            case Maestro:
                return C0880R.C0881drawable.maestro_card_icon;
            default:
                return C0880R.C0881drawable.creditcard_default_icon;
        }
    }
}
