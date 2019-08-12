package com.airbnb.android.lib.payments.creditcard.brazil.textinput.watcher;

import android.text.Editable;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilTextFormatterStrategy;
import com.airbnb.android.utils.SimpleTextWatcher;

public class BrazilCreditCardTextWatcher extends SimpleTextWatcher {
    private final BrazilTextFormatterStrategy formatTextStrategy;
    private final BrazilCreditCardTextChangedListener textChangedListener;

    public interface BrazilCreditCardTextChangedListener {
        void onTextChanged(String str);
    }

    public BrazilCreditCardTextWatcher(BrazilTextFormatterStrategy formatTextStrategy2, BrazilCreditCardTextChangedListener textChangedListener2) {
        this.formatTextStrategy = formatTextStrategy2;
        this.textChangedListener = textChangedListener2;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.textChangedListener.onTextChanged(s.toString());
    }

    public void afterTextChanged(Editable s) {
        this.formatTextStrategy.formatText(s.toString());
    }
}
