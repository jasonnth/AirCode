package com.airbnb.android.utils;

import android.content.Context;
import com.airbnb.utils.R;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class CurrencyUtils {
    private static final int MAX_PRECISION_FX = 5;
    private static NumberFormat currencyInstance;

    private CurrencyUtils() {
    }

    private static NumberFormat getNumberFormatCurrencyInstance() {
        if (currencyInstance == null) {
            currencyInstance = NumberFormat.getCurrencyInstance();
        }
        return currencyInstance;
    }

    public static synchronized String formatAmount(double priceNative, Currency currency, int maximumFractionDigits) {
        String format;
        synchronized (CurrencyUtils.class) {
            NumberFormat numberFormat = getNumberFormatCurrencyInstance();
            numberFormat.setCurrency(currency);
            numberFormat.setMaximumFractionDigits(maximumFractionDigits);
            format = numberFormat.format(priceNative);
        }
        return format;
    }

    public static String formatAmount(double priceNative, Currency currency) {
        return formatAmount(priceNative, currency, 0);
    }

    public static String formatCurrencyAmount(double priceNative, String currencyCode) {
        return formatAmount(priceNative, Currency.getInstance(currencyCode));
    }

    public static String formatCurrencyAmountForFXCopy(double priceNative, String currencyCode) {
        return formatAmount(priceNative, Currency.getInstance(currencyCode), 5);
    }

    public static boolean isUserPreferredCurrencyCNY(String currencyCode) {
        return AirbnbConstants.CURRENCY_CHINA.equalsIgnoreCase(currencyCode);
    }

    public static String getFormattedPrice(double priceNative, String currencyCode) {
        try {
            return formatCurrencyAmount(priceNative, currencyCode);
        } catch (IllegalArgumentException e) {
            return currencyCode + priceNative;
        }
    }

    public static String getSanitizedSymbol(Context context, String code, String symbol) {
        if (code == null) {
            throw new IllegalStateException("Currency code must not be null");
        }
        char c = 65535;
        switch (code.hashCode()) {
            case 81503:
                if (code.equals("RUB")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return context.getString(R.string.currency_symbol_russian_ruble);
            default:
                return symbol;
        }
    }

    public static Currency getLocalDefaultCurrency() {
        return NumberFormat.getCurrencyInstance(Locale.getDefault()).getCurrency();
    }
}
