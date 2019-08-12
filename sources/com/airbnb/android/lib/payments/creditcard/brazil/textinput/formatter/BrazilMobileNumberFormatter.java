package com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter;

import com.airbnb.p027n2.components.PaymentInputLayout;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class BrazilMobileNumberFormatter implements BrazilTextFormatterStrategy {
    private final BrazilPaymentInputFormatter inputFormatter;
    private final PaymentInputLayout paymentInputLayout;
    private boolean shouldFormat = true;
    private boolean shouldRemoveFormatting = true;

    public BrazilMobileNumberFormatter(BrazilPaymentInputFormatter inputFormatter2, PaymentInputLayout paymentInputLayout2) {
        this.inputFormatter = inputFormatter2;
        this.paymentInputLayout = paymentInputLayout2;
    }

    public void formatText(String text) {
        PhoneNumber phoneNumber = this.inputFormatter.parsePhoneNumber(text);
        if (this.inputFormatter.isValidBrazilianPhoneNumber(phoneNumber)) {
            formatPhoneNumber(phoneNumber);
        } else {
            removePhoneNumberFormatting(text);
        }
    }

    private void formatPhoneNumber(PhoneNumber phoneNumber) {
        if (this.shouldFormat) {
            this.shouldFormat = false;
            this.paymentInputLayout.setText((CharSequence) this.inputFormatter.formatPhoneNumber(phoneNumber));
            this.paymentInputLayout.setSelection(this.paymentInputLayout.getText().length());
            return;
        }
        this.shouldFormat = true;
    }

    private void removePhoneNumberFormatting(String input) {
        if (this.shouldRemoveFormatting) {
            this.shouldRemoveFormatting = false;
            String trimmed = input.replaceAll("[\\s)(-]", "");
            this.paymentInputLayout.setText((CharSequence) trimmed);
            this.paymentInputLayout.setSelection(trimmed.length());
            return;
        }
        this.shouldRemoveFormatting = true;
    }
}
