package com.airbnb.android.core.models.payments;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;
import com.braintreepayments.api.models.CardBuilder;
import com.facebook.appevents.AppEventsConstants;

public class BraintreeCreditCard extends BraintreePaymentInstrument {
    private static final int CARD_LENGTH_SHORT = 4;
    private static final long serialVersionUID = 2230641723614694555L;
    private String mCCV;
    private CardType mCardType;
    private String mCountryCode;
    private String mCreditCardNumber;
    private String mExpiryMonth;
    private String mExpiryYear;
    private String mPostalCode;
    private String updatedPostalCode;

    public void setLastFourDigits(String value) {
        setCardNumber(value);
    }

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

    public BraintreeCreditCard() {
    }

    public BraintreeCreditCard(String cvv) {
        this(null, "", "", "", "", "", cvv);
    }

    public BraintreeCreditCard(CardType cardType, String countryCode, String postal, String expiryMonth, String expiryYear, String cardNumber, String ccv) {
        setCardType(cardType);
        this.mCountryCode = countryCode;
        this.mPostalCode = postal;
        setExpiryMonth(expiryMonth);
        setExpiryYear(expiryYear);
        setCardNumber(cardNumber);
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

    public String getExpiryMonth() {
        return this.mExpiryMonth;
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

    public boolean isFullPaymentInstrument() {
        return this.mCreditCardNumber == null || this.mCreditCardNumber.length() != 4;
    }

    public boolean isEditable() {
        return isFullPaymentInstrument();
    }

    public int getIcon() {
        return this.mCardType.getIcon();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BraintreeCreditCard)) {
            return false;
        }
        BraintreeCreditCard that = (BraintreeCreditCard) o;
        if (this.mCardType != that.mCardType || !getLastFourDigits().equals(that.getLastFourDigits())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mCardType.hashCode() * 31) + getLastFourDigits().hashCode();
    }

    public InstrumentType getType() {
        return InstrumentType.BraintreeCreditCard;
    }

    public CardBuilder buildCard() {
        return (CardBuilder) ((CardBuilder) ((CardBuilder) ((CardBuilder) ((CardBuilder) ((CardBuilder) new CardBuilder().cardNumber(getCardNumber())).cvv(getSecurityCode())).expirationMonth(getExpiryMonth())).expirationYear(getExpiryYear())).postalCode(getPostalCode())).countryName(getCountry());
    }

    public void setUpdatedPostalCode(String updatedPostalCode2) {
        this.updatedPostalCode = updatedPostalCode2;
    }

    public String getUpdatedPostalCode() {
        return this.updatedPostalCode;
    }

    public boolean hasUpdatedPostalCode() {
        return !TextUtils.isEmpty(this.updatedPostalCode);
    }

    public void setPostalCode(String postalCode) {
        this.mPostalCode = postalCode;
    }

    public String getDisplayName(Context context) {
        return context.getString(C0716R.string.space_separated, new Object[]{context.getString(getCardType().getName()), getLastFourDigits()});
    }
}
