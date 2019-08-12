package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.businesstravel.models.BusinessEntityGroup;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.models.generated.GenPaymentOption;
import com.airbnb.android.core.models.payments.AlipayPaymentInstrument;
import com.airbnb.android.core.models.payments.AlipayRedirectPaymentInstrument;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.BusinessTravelPaymentInstruments;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.utils.TextUtil;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class PaymentOption extends GenPaymentOption {
    public static final Creator<PaymentOption> CREATOR = new Creator<PaymentOption>() {
        public PaymentOption[] newArray(int size) {
            return new PaymentOption[size];
        }

        public PaymentOption createFromParcel(Parcel source) {
            PaymentOption object = new PaymentOption();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final int INVALID_ID = 0;
    private static final String PAYMENT_METHOD_TYPE_ALIPAY = "alipay_direct";
    private static final String PAYMENT_METHOD_TYPE_ALIPAY_REDIRECT = "alipay_redirect";
    private static final String PAYMENT_METHOD_TYPE_ANDROID_PAY = "android_pay";
    private static final String PAYMENT_METHOD_TYPE_BRAINTREE_PAYPAL = "braintree_paypal";
    private static final String PAYMENT_METHOD_TYPE_BUSINESS_CENTRALIZED_BILLING = "business_travel_centralized_billing";
    private static final String PAYMENT_METHOD_TYPE_BUSINESS_INVOICE = "business_travel_invoice";
    private static final String PAYMENT_METHOD_TYPE_CARD = "cc";
    private String cvvPayload;
    private long gibraltarInstrumentId;
    private boolean isCvvVerified;

    public boolean isAlipayRedirect() {
        return PAYMENT_METHOD_TYPE_ALIPAY_REDIRECT.equals(this.mPaymentMethodType);
    }

    public OldPaymentInstrument toLegacyPaymentInstrument() {
        return toBasePaymentInstrument(false);
    }

    public OldPaymentInstrument toPaymentInstrumentWithGibraltarId() {
        return toBasePaymentInstrument(true);
    }

    private OldPaymentInstrument toBasePaymentInstrument(boolean toGibraltarInstrument) {
        OldPaymentInstrument instrument;
        if (PAYMENT_METHOD_TYPE_CARD.equals(getPaymentMethodType())) {
            instrument = new BraintreeCreditCard();
            ((BraintreeCreditCard) instrument).setCardType(getCreditCardType());
            ((BraintreeCreditCard) instrument).setLastFourDigits(getCreditCardLastFour());
        } else if (PAYMENT_METHOD_TYPE_BRAINTREE_PAYPAL.equals(getPaymentMethodType())) {
            instrument = new PayPalInstrument();
            ((PayPalInstrument) instrument).setEmail(getName());
        } else if (PAYMENT_METHOD_TYPE_ALIPAY.equals(getPaymentMethodType())) {
            instrument = new AlipayPaymentInstrument();
        } else if (PAYMENT_METHOD_TYPE_BUSINESS_INVOICE.equals(getPaymentMethodType()) || PAYMENT_METHOD_TYPE_BUSINESS_CENTRALIZED_BILLING.equals(getPaymentMethodType())) {
            BusinessEntityGroup businessEntityGroup = getBusinessEntityGroup();
            instrument = new BusinessTravelPaymentInstruments(businessEntityGroup.getId(), businessEntityGroup.getPaymentMethodDisplayName(), getPaymentMethodType());
        } else if (PAYMENT_METHOD_TYPE_ANDROID_PAY.equals(getPaymentMethodType())) {
            instrument = new AndroidPayInstrument();
        } else if (!PAYMENT_METHOD_TYPE_ALIPAY_REDIRECT.equals(getPaymentMethodType())) {
            return null;
        } else {
            instrument = new AlipayRedirectPaymentInstrument();
        }
        if (toGibraltarInstrument) {
            instrument.setId(getGibraltarInstrumentId());
        } else {
            instrument.setId(getLegacyInstrumentId());
        }
        instrument.setName(getName());
        return instrument;
    }

    public static List<OldPaymentInstrument> toValidPaymentInstruments(List<PaymentOption> paymentOptions) {
        return FluentIterable.from((Iterable<E>) paymentOptions).filter(PaymentOption$$Lambda$1.lambdaFactory$()).transform(PaymentOption$$Lambda$2.lambdaFactory$()).filter(PaymentOption$$Lambda$3.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$toValidPaymentInstruments$0(PaymentOption option) {
        return option.isAlipayRedirect() || option.isExistingInstrument() || option.getBusinessEntityGroup() != null;
    }

    static /* synthetic */ boolean lambda$toValidPaymentInstruments$1(OldPaymentInstrument option) {
        return option != null;
    }

    public boolean shouldDisplayFXCopy() {
        PaymentSettlementQuote quote = getSettlementQuote();
        return !quote.getTargetCurrencyUnit().getCurrency().equals(quote.getSettlementCurrencyUnit().getCurrency());
    }

    public String getFXCopy(Context context, String totalCharge) {
        PaymentSettlementQuote quote = getSettlementQuote();
        CurrencyAmount settlementCurrencyUnit = quote.getSettlementCurrencyUnit();
        CurrencyAmount targetCurrencyUnit = quote.getTargetCurrencyUnit();
        String priceDifferenceText = context.getString(C0716R.string.fx_payment_charge, new Object[]{settlementCurrencyUnit.getCurrency(), totalCharge});
        String fromCurrencyText = settlementCurrencyUnit.formattedForFXCopyDisplay();
        String toCurrencyText = targetCurrencyUnit.formattedForFXCopyDisplay();
        StringBuilder sb = new StringBuilder();
        sb.append(priceDifferenceText + " ");
        if (TextUtils.isEmpty(quote.getFxFeeRate())) {
            sb.append(context.getString(C0716R.string.fx_payment_conversion, new Object[]{fromCurrencyText, toCurrencyText}));
        } else {
            sb.append(context.getString(C0716R.string.fx_payment_conversion_fee, new Object[]{fromCurrencyText, toCurrencyText, quote.getFxFeeRate()}));
        }
        return sb.toString();
    }

    public String getDisplayName(Context context) {
        switch (PaymentMethodType.findByServerKey(getPaymentMethodType())) {
            case CreditCard:
            case DigitalRiverCreditCard:
                return context.getString(C0716R.string.space_separated, new Object[]{context.getString(CardType.getCardType(getCreditCardType()).getName()), getCreditCardLastFour()});
            case PayPal:
                return getName();
            case PayU:
                return context.getString(C0716R.string.payment_type_credit_or_debit_card);
            case iDEAL:
                return context.getString(C0716R.string.payment_type_ideal);
            case Sofort:
                return context.getString(C0716R.string.payment_type_sofort);
            case Alipay:
                return hasValidId() ? getName() : context.getString(C0716R.string.alipay);
            case AndroidPay:
                return context.getString(C0716R.string.payment_type_android_pay);
            case AmexExpressCheckout:
                return getName();
            case BusinessTravelInvoice:
            case BusinessTravelCentralBilling:
                return getBusinessEntityGroup().getPaymentMethodDisplayName();
            default:
                return "";
        }
    }

    public String getPaymentOptionRowSubtitle(Context context) {
        switch (PaymentMethodType.findByServerKey(getPaymentMethodType())) {
            case CreditCard:
            case DigitalRiverCreditCard:
                if (this.mExpireDate == null) {
                    return null;
                }
                return context.getString(C0716R.string.payment_option_row_subtitle_text_with_expiration, new Object[]{this.mSettlementCurrency, this.mExpireDate});
            default:
                return null;
        }
    }

    public boolean hasValidGibraltarId() {
        return this.gibraltarInstrumentId > 0;
    }

    private boolean hasValidId() {
        return getLegacyInstrumentId() > 0;
    }

    public boolean isCvvVerified() {
        return this.isCvvVerified;
    }

    public void setCvvVerified(boolean isCvvVerified2) {
        this.isCvvVerified = isCvvVerified2;
    }

    public void setCvvPayload(String cvvPayload2) {
        this.cvvPayload = cvvPayload2;
    }

    public String getCvvPayload() {
        return this.cvvPayload;
    }

    public boolean isBusinessTravelPaymentOption() {
        return PaymentMethodType.findByServerKey(getPaymentMethodType()) == PaymentMethodType.BusinessTravelCentralBilling || PaymentMethodType.findByServerKey(getPaymentMethodType()) == PaymentMethodType.BusinessTravelInvoice;
    }

    public boolean isAndroidPay() {
        return PaymentMethodType.findByServerKey(getPaymentMethodType()) == PaymentMethodType.AndroidPay;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeString(this.cvvPayload);
        parcel.writeLong(this.gibraltarInstrumentId);
        parcel.writeByte((byte) (this.isCvvVerified ? 1 : 0));
    }

    public void readFromParcel(Parcel source) {
        super.readFromParcel(source);
        this.cvvPayload = source.readString();
        this.gibraltarInstrumentId = source.readLong();
        this.isCvvVerified = source.readByte() != 0;
    }

    public boolean equals(Object obj) {
        PaymentOption paymentOption = (PaymentOption) obj;
        return getGibraltarInstrumentId() == paymentOption.getGibraltarInstrumentId() && TextUtil.equals(getName(), paymentOption.getName()) && TextUtil.equals(getPaymentMethodType(), paymentOption.getPaymentMethodType());
    }
}
