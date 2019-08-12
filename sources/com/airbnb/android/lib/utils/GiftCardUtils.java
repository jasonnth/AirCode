package com.airbnb.android.lib.utils;

import android.content.Context;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.lib.AirbnbApplication;
import java.util.Locale;

public final class GiftCardUtils {
    public static final String SUPPORTED_CURRENCY_USD = "USD";

    private GiftCardUtils() {
    }

    public static boolean isGiftCreditEnabled(Context context) {
        return Trebuchet.launch(TrebuchetKeys.KEY_GIFT_CREDIT_V2) && isQualifyingLocale() && isQualifyingCurrency(context);
    }

    public static boolean isGiftCreditEnabled(String currencyCode) {
        return Trebuchet.launch(TrebuchetKeys.KEY_GIFT_CREDIT_V2) && isQualifyingLocale() && isQualifyingCurrency(currencyCode);
    }

    public static boolean isGiftCardsEnabled() {
        return Trebuchet.launch(TrebuchetKeys.KEY_GIFT_CARDS_V2) && isQualifyingLocale();
    }

    private static boolean isQualifyingLocale() {
        return Locale.US.getCountry().equals(Locale.getDefault().getCountry());
    }

    private static boolean isQualifyingCurrency(Context context) {
        return SUPPORTED_CURRENCY_USD.equals(AirbnbApplication.instance(context).component().currencyHelper().getLocalCurrencyString());
    }

    private static boolean isQualifyingCurrency(String currencyCode) {
        return SUPPORTED_CURRENCY_USD.equals(currencyCode);
    }
}
