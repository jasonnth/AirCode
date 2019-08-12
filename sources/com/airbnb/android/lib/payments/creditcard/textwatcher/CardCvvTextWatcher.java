package com.airbnb.android.lib.payments.creditcard.textwatcher;

import android.text.Editable;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.utils.SimpleTextWatcher;

public class CardCvvTextWatcher extends SimpleTextWatcher {
    private CardType cardType;
    private final CreditCardValidator cardValidator;
    private final CardCvvTextListener listener;

    public interface CardCvvTextListener {
        void hideCardCvvError();

        void onFullCardCvvEntered();

        void showCardCvvError();
    }

    public CardCvvTextWatcher(CardCvvTextListener listener2, CreditCardValidator cardValidator2) {
        this.listener = listener2;
        this.cardValidator = cardValidator2;
    }

    public void updateCardType(CardType cardType2) {
        this.cardType = cardType2;
    }

    public void afterTextChanged(Editable s) {
        validateCvv(s.toString());
    }

    private void validateCvv(String securityCode) {
        if (this.cardType == null) {
            return;
        }
        if (this.cardValidator.isCardCvvFullLength(securityCode, this.cardType)) {
            this.listener.onFullCardCvvEntered();
        } else if (this.cardValidator.isCardCvvTooLong(securityCode, this.cardType)) {
            this.listener.showCardCvvError();
        } else {
            this.listener.hideCardCvvError();
        }
    }
}
