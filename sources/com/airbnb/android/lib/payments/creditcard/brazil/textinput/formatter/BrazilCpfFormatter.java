package com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter;

import com.airbnb.p027n2.components.PaymentInputLayout;

public class BrazilCpfFormatter implements BrazilTextFormatterStrategy {
    public static final int MAX_CPF_LENGTH = 14;
    private final BrazilPaymentInputFormatter inputFormatter;
    private final PaymentInputLayout paymentInputLayout;

    public BrazilCpfFormatter(BrazilPaymentInputFormatter inputFormatter2, PaymentInputLayout paymentInputLayout2) {
        this.inputFormatter = inputFormatter2;
        this.paymentInputLayout = paymentInputLayout2;
    }

    public void formatText(String text) {
        String input = text.replaceAll("\\s+", "");
        String formattedCpf = this.inputFormatter.formatCpf(input);
        if (!input.equals(formattedCpf)) {
            this.paymentInputLayout.setText((CharSequence) formattedCpf);
            this.paymentInputLayout.setSelection(this.paymentInputLayout.getText().length());
        }
    }
}
