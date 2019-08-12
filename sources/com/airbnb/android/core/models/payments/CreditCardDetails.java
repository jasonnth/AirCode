package com.airbnb.android.core.models.payments;

import android.os.Parcelable;
import com.airbnb.android.core.enums.CardType;

public abstract class CreditCardDetails implements Parcelable {

    public static abstract class Builder {
        public abstract CreditCardDetails build();

        public abstract Builder cardNumber(String str);

        public abstract Builder countryCode(String str);

        public abstract Builder cvv(String str);

        public abstract Builder expirationMonth(String str);

        public abstract Builder expirationYear(String str);

        public abstract Builder postalCode(String str);
    }

    public abstract String cardNumber();

    public abstract String countryCode();

    public abstract String cvv();

    public abstract String expirationMonth();

    public abstract String expirationYear();

    public abstract String postalCode();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }

    public BraintreeCreditCard toCreditCard() {
        return new BraintreeCreditCard(CardType.getCardTypeFromCCNumber(cardNumber()), countryCode(), postalCode(), expirationMonth(), expirationYear(), cardNumber(), cvv());
    }
}
