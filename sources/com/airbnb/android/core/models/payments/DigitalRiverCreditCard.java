package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;
import com.facebook.appevents.AppEventsConstants;

public class DigitalRiverCreditCard extends OldPaymentInstrument {
    private static final int CARD_LENGTH_SHORT = 4;
    private String mCCV;
    private CardType mCardType;
    private String mCountryCode;
    private String mCreditCardNumber;
    private String mExpiryMonth;
    private String mExpiryYear;
    private String mPostalCode;

    public CardType getCardType() {
        return this.mCardType;
    }

    public void setCardType(String value) {
        this.mCardType = CardType.getCardType(value);
    }

    public void setCardType(CardType type) {
        if (type == null) {
            type = CardType.Unknown;
        }
        this.mCardType = type;
    }

    private DigitalRiverCreditCard() {
    }

    public static DigitalRiverCreditCard fromCreditCard(BraintreeCreditCard cc) {
        return new DigitalRiverCreditCard(cc.getCardType(), cc.getCountry(), cc.getPostalCode(), cc.getExpiryMonth(), cc.getExpiryYear(), cc.getCardNumber(), cc.getSecurityCode());
    }

    public DigitalRiverCreditCard(CardType cardType, String countryCode, String postal, String expiryMonth, String expiryYear, String creditCard, String ccv) {
        setCardType(cardType);
        this.mCountryCode = countryCode;
        this.mPostalCode = postal;
        setExpiryMonth(expiryMonth);
        setExpiryYear(expiryYear);
        setCardNumber(creditCard);
        this.mCCV = ccv;
    }

    public String getCardNumber() {
        return this.mCreditCardNumber;
    }

    /* access modifiers changed from: protected */
    public void setCardNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Credit card number cannot be null");
        }
        this.mCreditCardNumber = number;
    }

    /* access modifiers changed from: protected */
    public void setExpiryYear(String expiryYear) {
        if (expiryYear.length() == 2) {
            expiryYear = "20" + expiryYear;
        }
        this.mExpiryYear = expiryYear;
    }

    /* access modifiers changed from: protected */
    public void setExpiryMonth(String expiryMonth) {
        if (expiryMonth.length() == 1) {
            expiryMonth = AppEventsConstants.EVENT_PARAM_VALUE_NO + expiryMonth;
        }
        this.mExpiryMonth = expiryMonth;
    }

    public String getExpiryMonth() {
        return this.mExpiryMonth;
    }

    public String getExpiryYear() {
        return this.mExpiryYear;
    }

    public String getCountry() {
        return this.mCountryCode;
    }

    public void setCountry(String country) {
        this.mCountryCode = country;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public String getSecurityCode() {
        return this.mCCV;
    }

    public String getLastFourDigits() {
        if (this.mCreditCardNumber.length() < 4) {
            return this.mCreditCardNumber;
        }
        return this.mCreditCardNumber.substring(this.mCreditCardNumber.length() - 4);
    }

    public boolean isEditable() {
        return this.mCreditCardNumber == null || this.mCreditCardNumber.length() != 4;
    }

    public int getIcon() {
        return this.mCardType.getIcon();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DigitalRiverCreditCard)) {
            return false;
        }
        DigitalRiverCreditCard that = (DigitalRiverCreditCard) o;
        if (this.mCardType != that.mCardType || !getLastFourDigits().equals(that.getLastFourDigits())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mCardType.hashCode() * 31) + getLastFourDigits().hashCode();
    }

    public InstrumentType getType() {
        return InstrumentType.DigitalRiverCreditCard;
    }

    public String getDisplayName(Context context) {
        return context.getString(C0716R.string.space_separated, new Object[]{context.getString(getCardType().getName()), getLastFourDigits()});
    }
}
