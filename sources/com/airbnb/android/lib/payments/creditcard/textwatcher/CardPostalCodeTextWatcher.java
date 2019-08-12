package com.airbnb.android.lib.payments.creditcard.textwatcher;

import android.text.Editable;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.utils.SimpleTextWatcher;

public class CardPostalCodeTextWatcher extends SimpleTextWatcher {
    private final CreditCardValidator cardValidator;
    private final CardPostalCodeTextListener listener;

    public interface CardPostalCodeTextListener {
        void onFullPostalCodeEntered();
    }

    public CardPostalCodeTextWatcher(CardPostalCodeTextListener listener2, CreditCardValidator cardValidator2) {
        this.listener = listener2;
        this.cardValidator = cardValidator2;
    }

    public void afterTextChanged(Editable s) {
        if (this.cardValidator.isPostalCodeFullLength(s.toString())) {
            this.listener.onFullPostalCodeEntered();
        }
    }
}
