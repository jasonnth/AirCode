package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.requests.UpdateCurrencyRequest;
import com.airbnb.android.utils.AirbnbConstants;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class CurrencyFormatter {
    private static final String CURRENCY_CHANGE = "currency_change";
    private Currency localCurrency;
    private final AirbnbAccountManager mAccountManager;
    private final Context mContext;
    private final AirbnbPreferences mPreferences;
    private NumberFormat numberFormat;

    public CurrencyFormatter(Context context, AirbnbAccountManager accountManager, AirbnbPreferences preferences) {
        this.mContext = context;
        this.mAccountManager = accountManager;
        this.mPreferences = preferences;
        allocateFormatters();
    }

    private void allocateFormatters() {
        Locale locale = Locale.getDefault();
        String currencyCode = this.mPreferences.getSharedPreferences().getString(AirbnbConstants.PREFS_CURRENCY, "");
        if (TextUtils.isEmpty(currencyCode)) {
            this.numberFormat = NumberFormat.getCurrencyInstance(locale);
            this.numberFormat.setMaximumFractionDigits(0);
            this.localCurrency = this.numberFormat.getCurrency();
            return;
        }
        setCurrency(currencyCode, false, false);
    }

    public static String formatCurrencyAmount(CurrencyAmount amount) {
        return amount.formattedForDisplay();
    }

    public String getLocalCurrencySymbol() {
        return this.localCurrency.getSymbol();
    }

    public String getLocalCurrencyString() {
        return this.localCurrency.getCurrencyCode();
    }

    public String formatNativeCurrency(double priceNative, boolean includeSymbol) {
        String result = this.numberFormat.format(priceNative);
        if (!includeSymbol) {
            return trimCurrencySymbol(result);
        }
        return result;
    }

    private String trimCurrencySymbol(String value) {
        int begin = 0;
        int end = 0;
        int i = 0;
        while (true) {
            if (i >= value.length()) {
                break;
            } else if (Character.isDigit(value.charAt(i))) {
                begin = i;
                break;
            } else {
                i++;
            }
        }
        int i2 = value.length() - 1;
        while (true) {
            if (i2 <= 0) {
                break;
            } else if (Character.isDigit(value.charAt(i2))) {
                end = i2;
                break;
            } else {
                i2--;
            }
        }
        return value.substring(begin, end + 1);
    }

    public boolean isSymbolPrefixed() {
        if (!Character.isDigit(this.numberFormat.format(1).charAt(0))) {
            return true;
        }
        return false;
    }

    public void setCurrency(String currencyCode, boolean fromUser, boolean restartTask) {
        Editor editor = this.mPreferences.getSharedPreferences().edit();
        editor.putString(AirbnbConstants.PREFS_CURRENCY, currencyCode);
        editor.putBoolean(AirbnbConstants.PREFS_CURRENCY_IS_USER_SET, fromUser);
        editor.apply();
        this.numberFormat = NumberFormat.getCurrencyInstance();
        this.localCurrency = Currency.getInstance(currencyCode);
        this.numberFormat.setCurrency(this.localCurrency);
        this.numberFormat.setMaximumFractionDigits(0);
        if (fromUser && this.mAccountManager.isCurrentUserAuthorized()) {
            new UpdateCurrencyRequest(currencyCode).execute(NetworkUtil.singleFireExecutor());
        }
        if (restartTask) {
            this.mContext.startActivity(new Intent(this.mContext, Activities.home()).addFlags(335544320).setAction(CURRENCY_CHANGE));
        }
    }

    public String formatNativeUSCurrency(double priceNative, boolean includeSymbol) {
        String result = NumberFormat.getCurrencyInstance(Locale.US).format(priceNative);
        if (!includeSymbol) {
            return trimCurrencySymbol(result);
        }
        return result;
    }

    public String getSymbolicPrice(int levelExpense) {
        String currencySymbol = getLocalCurrencySymbol();
        char[] priceArray = new char[levelExpense];
        Arrays.fill(priceArray, currencySymbol.length() == 1 ? currencySymbol.charAt(0) : '$');
        return new String(priceArray);
    }

    public String getAndroidPayFormattedPrice(int totalPrice) {
        return formatNativeUSCurrency((double) totalPrice, false).replaceAll(",", "");
    }
}
