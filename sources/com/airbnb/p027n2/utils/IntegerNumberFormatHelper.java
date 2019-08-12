package com.airbnb.p027n2.utils;

import android.util.Log;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;

/* renamed from: com.airbnb.n2.utils.IntegerNumberFormatHelper */
public class IntegerNumberFormatHelper {

    /* renamed from: com.airbnb.n2.utils.IntegerNumberFormatHelper$IntegerPercentageNumberFormat */
    private static class IntegerPercentageNumberFormat extends NumberFormat {
        private final NumberFormat percentageFormat;

        public IntegerPercentageNumberFormat(Locale locale) {
            this.percentageFormat = NumberFormat.getPercentInstance(locale);
            this.percentageFormat.setMaximumFractionDigits(0);
            this.percentageFormat.setMaximumIntegerDigits(2);
        }

        public StringBuffer format(double value, StringBuffer buffer, FieldPosition field) {
            if (value % 1.0d > 0.0d) {
                Log.e("IPNF", "Integer expected as per definition, losing decimal precision");
            }
            return format(Math.round(value), buffer, field);
        }

        public StringBuffer format(long value, StringBuffer buffer, FieldPosition field) {
            if (value > 2147483647L || value < -2147483648L) {
                Log.e("IPNF", "Integer expected as per definition, converting to 0");
                value = 0;
            }
            return formatEnforceWithInteger((int) value, buffer, field);
        }

        public Number parse(String string, ParsePosition position) {
            return Integer.valueOf(toIntegerPercentage(this.percentageFormat.parse(string, position).doubleValue()));
        }

        private StringBuffer formatEnforceWithInteger(int value, StringBuffer buffer, FieldPosition field) {
            return this.percentageFormat.format(((double) value) / 100.0d, buffer, field);
        }

        private int toIntegerPercentage(double value) {
            return (int) (100.0d * value);
        }
    }

    public static NumberFormat forInteger(int maxDigits) {
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        integerFormat.setMaximumIntegerDigits(maxDigits);
        return integerFormat;
    }

    public static NumberFormat forCurrency(Locale locale, Currency currency) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        currencyFormat.setCurrency(currency);
        currencyFormat.setMaximumFractionDigits(0);
        return currencyFormat;
    }

    public static NumberFormat forCurrency(Currency currency) {
        return forCurrency(Locale.getDefault(), currency);
    }

    public static NumberFormat forIntegerPercentage() {
        return forIntegerPercentage(Locale.getDefault());
    }

    public static NumberFormat forIntegerPercentage(Locale locale) {
        return new IntegerPercentageNumberFormat(locale);
    }
}
