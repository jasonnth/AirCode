package com.airbnb.android.lib.payments.creditcard.textwatcher;

import android.text.Editable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.facebook.appevents.AppEventsConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CardDateTextWatcher extends SimpleTextWatcher {
    private final int MAX_INPUT_LENGTH = (" / ".length() + 4);
    private final PaymentInputLayout cardDateInputLayout;
    private final CreditCardValidator cardValidator;
    private final Calendar expirationDate;
    private final SimpleDateFormat formatter;
    private final CardDateTextListener listener;
    private String previousInput = "";
    private final String separator = " / ";

    public interface CardDateTextListener {
        void hideCardDateError();

        void onFullCardDateEntered();

        void showCardDateError();
    }

    public CardDateTextWatcher(CardDateTextListener listener2, CreditCardValidator cardValidator2, PaymentInputLayout cardDateInputLayout2, SimpleDateFormat formatter2, Calendar expirationDate2) {
        this.listener = listener2;
        this.cardValidator = cardValidator2;
        this.cardDateInputLayout = cardDateInputLayout2;
        this.formatter = formatter2;
        this.expirationDate = expirationDate2;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.previousInput = s.toString();
    }

    public void afterTextChanged(Editable s) {
        boolean didDelete;
        this.listener.hideCardDateError();
        String input = s.toString();
        if (this.previousInput.length() > input.length()) {
            didDelete = true;
        } else {
            didDelete = false;
        }
        if (input.length() > this.MAX_INPUT_LENGTH) {
            formatDate(input.substring(0, input.length() - 1));
        }
        try {
            this.expirationDate.setTime(this.formatter.parse(input));
            if (input.length() == this.MAX_INPUT_LENGTH) {
                this.listener.onFullCardDateEntered();
                validateDate();
            }
        } catch (ParseException e) {
            String formattedInput = formatInput(input, didDelete);
            if (!formattedInput.equals(input)) {
                formatDate(formattedInput);
            }
        }
    }

    public Calendar getExpirationDate() {
        return this.expirationDate;
    }

    private String formatInput(String input, boolean didDelete) {
        String formattedInput = input;
        if (didDelete) {
            if (input.endsWith(" / ")) {
                return input.split(" / ")[0];
            }
            if (input.endsWith(" /")) {
                return input.split(" /")[0];
            }
            return formattedInput;
        } else if (input.length() == 1) {
            if (Integer.parseInt(input) > 1) {
                return AppEventsConstants.EVENT_PARAM_VALUE_NO + input + " / ";
            }
            return formattedInput;
        } else if (input.length() == 2) {
            return input + " / ";
        } else {
            if (input.length() == 3) {
                return input.substring(0, 2) + " / " + input.substring(input.length() - 1);
            }
            return formattedInput;
        }
    }

    private void formatDate(String date) {
        this.cardDateInputLayout.setText((CharSequence) date);
        this.cardDateInputLayout.setSelection(date.length());
    }

    private void validateDate() {
        if (this.cardValidator.isValidExpiryDate(this.expirationDate, AirDate.today())) {
            this.listener.hideCardDateError();
        } else {
            this.listener.showCardDateError();
        }
    }
}
