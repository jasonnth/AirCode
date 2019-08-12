package com.airbnb.android.lib.payments.creditcard.validation;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.CardType;
import java.util.Calendar;

public class CreditCardValidator {
    public boolean areCardDetailsEntered(String cardNumber, String expiryDate, String cvv, String postalCode) {
        return !cardNumber.isEmpty() && !expiryDate.isEmpty() && !cvv.isEmpty() && !postalCode.isEmpty();
    }

    public boolean isValidCardNumber(String cardNumber, CardType cardType) {
        return CardType.isValidCCNumber(cardNumber, cardType);
    }

    public boolean isCardNumberFullLength(String cardNumber, CardType cardType) {
        return CardType.isCardNumberFullLength(cardNumber, cardType);
    }

    public boolean cardNumberExceedsMaxLength(String cardNumber, CardType cardType) {
        return cardType != CardType.Unknown && cardNumber.replace(" ", "").length() > cardType.getCardNumberMaxLength();
    }

    public boolean isValidExpiryDate(Calendar expirationDate, AirDate todayDate) {
        return todayDate.isBefore(AirDate.fromCalendar(expirationDate).plusMonths(1));
    }

    public boolean isCardCvvFullLength(String cvv, CardType cardType) {
        return CardType.isSecurityCodeFullLength(cvv, cardType);
    }

    public boolean isCardCvvTooLong(String cvv, CardType cardType) {
        return CardType.isSecurityCodeTooLong(cvv, cardType);
    }

    public boolean isPostalCodeFullLength(String postalCode) {
        return postalCode.length() >= 1;
    }

    public boolean isExpiryDateFullLength(String expiryDate) {
        return expiryDate.length() == 5;
    }
}
