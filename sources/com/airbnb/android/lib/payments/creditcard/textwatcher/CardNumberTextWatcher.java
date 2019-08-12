package com.airbnb.android.lib.payments.creditcard.textwatcher;

import android.text.Editable;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.PaymentInputLayout;

public class CardNumberTextWatcher extends SimpleTextWatcher {
    private final PaymentInputLayout cardNumberInputLayout;
    private final CreditCardValidator cardValidator;
    private boolean didFormat;
    private final CardNumberTextListener listener;

    public interface CardNumberTextListener {
        void hideCardNumberError();

        void onCardNumberFormatted(CardType cardType);

        void onFullCardNumberEntered(CardType cardType, boolean z);

        void showCardNumberError();
    }

    public CardNumberTextWatcher(CardNumberTextListener listener2, CreditCardValidator cardValidator2, PaymentInputLayout cardNumberInputLayout2) {
        this.listener = listener2;
        this.cardValidator = cardValidator2;
        this.cardNumberInputLayout = cardNumberInputLayout2;
    }

    public void afterTextChanged(Editable s) {
        String cardNumber = s.toString().replaceAll("\\s+", "");
        CardType cardType = CardType.getCardTypeFromCCNumber(cardNumber);
        if (this.cardValidator.cardNumberExceedsMaxLength(cardNumber, cardType)) {
            formatCardNumber(cardNumber, cardType);
        } else if (this.didFormat) {
            this.didFormat = false;
        } else {
            this.listener.hideCardNumberError();
            if (cardType != CardType.Unknown) {
                formatCardNumber(cardNumber, cardType);
                if (this.cardValidator.isCardNumberFullLength(cardNumber, cardType)) {
                    boolean isValidCardNumber = this.cardValidator.isValidCardNumber(cardNumber, cardType);
                    fullCardNumberEntered(cardType, isValidCardNumber);
                    validateCardInput(isValidCardNumber);
                    return;
                }
            } else if (cardNumber.length() > 3) {
                this.listener.showCardNumberError();
                return;
            }
            this.listener.hideCardNumberError();
        }
    }

    private void validateCardInput(boolean isValidCardNumber) {
        if (isValidCardNumber) {
            this.listener.hideCardNumberError();
        } else {
            this.listener.showCardNumberError();
        }
    }

    private void formatCardNumber(String cardNumber, CardType cardType) {
        this.didFormat = true;
        this.cardNumberInputLayout.setText((CharSequence) cardType.formatCC(cardNumber));
        this.cardNumberInputLayout.setSelection(this.cardNumberInputLayout.getText().length());
        this.listener.onCardNumberFormatted(cardType);
    }

    private void fullCardNumberEntered(CardType cardType, boolean isValidCardNumber) {
        this.listener.onFullCardNumberEntered(cardType, isValidCardNumber);
    }
}
