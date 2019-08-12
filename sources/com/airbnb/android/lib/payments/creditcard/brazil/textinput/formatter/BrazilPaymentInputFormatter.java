package com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter;

import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.google.common.collect.FluentIterable;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class BrazilPaymentInputFormatter {
    private static final String BRAZIL_PHONE_COUNTRY_CODE = "55";
    private static final String CPF_INPUT_REGEX_FIRST_6 = "(\\d{3})(\\d{1})";
    private static final String CPF_INPUT_REGEX_FIRST_9 = "(\\d{3})(\\d{3})(\\d{1})";
    private static final String CPF_INPUT_REGEX_FULL = "(\\d{3})(\\d{3})(\\d{3})(\\d{1})";
    private final PhoneNumberUtil phoneNumberUtil;

    public BrazilPaymentInputFormatter(PhoneNumberUtil phoneNumberUtil2) {
        this.phoneNumberUtil = phoneNumberUtil2;
    }

    public String formatCpf(String input) {
        String trimmedInput = input.replaceAll("[.-]", "");
        int inputLength = trimmedInput.length();
        if (inputLength >= 3 && inputLength <= 6) {
            return trimmedInput.replaceAll(CPF_INPUT_REGEX_FIRST_6, "$1.$2");
        }
        if (inputLength > 6 && inputLength <= 9) {
            return trimmedInput.replaceAll(CPF_INPUT_REGEX_FIRST_9, "$1.$2.$3");
        }
        if (inputLength >= 10) {
            return trimmedInput.replaceAll(CPF_INPUT_REGEX_FULL, "$1.$2.$3-$4");
        }
        return input;
    }

    public PhoneNumber parsePhoneNumber(String input) {
        try {
            return this.phoneNumberUtil.parse(input, AirbnbConstants.COUNTRY_CODE_BRAZIL);
        } catch (NumberParseException e) {
            return new PhoneNumber();
        }
    }

    public String formatPhoneNumber(PhoneNumber input) {
        return this.phoneNumberUtil.format(input, PhoneNumberFormat.NATIONAL);
    }

    public boolean isValidBrazilianPhoneNumber(PhoneNumber phoneNumber) {
        return this.phoneNumberUtil.isValidNumberForRegion(phoneNumber, AirbnbConstants.COUNTRY_CODE_BRAZIL);
    }

    public String prependCountryCodeToPhoneNumber(String phoneNumber) {
        return BRAZIL_PHONE_COUNTRY_CODE + phoneNumber;
    }

    static /* synthetic */ boolean lambda$areInputsValid$0(PaymentInputLayout inputLayout) {
        return !inputLayout.isEmpty();
    }

    public boolean areInputsValid(PaymentInputLayout... paymentInputs) {
        return FluentIterable.from((E[]) paymentInputs).allMatch(BrazilPaymentInputFormatter$$Lambda$1.lambdaFactory$());
    }

    public boolean isCountryCodeInPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith(BRAZIL_PHONE_COUNTRY_CODE);
    }
}
