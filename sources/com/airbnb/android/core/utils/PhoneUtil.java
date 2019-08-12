package com.airbnb.android.core.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.utils.CallHelper;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.Locale;

public class PhoneUtil {
    private static final int ACTION_CALL = 0;
    private static final int ACTION_COPY = 1;
    private static final Integer[] CONTACT_US_ACTIONS = {Integer.valueOf(0), Integer.valueOf(1)};
    private static final String TAG = PhoneUtil.class.getSimpleName();
    private final Context context;

    public PhoneUtil(Context context2) {
        this.context = context2;
    }

    public PhoneNumber fetchPhoneNumber() {
        PhoneNumber phoneNumber = null;
        TelephonyManager telephone = (TelephonyManager) this.context.getSystemService("phone");
        if (telephone == null) {
            return phoneNumber;
        }
        String fetchedPhoneNumber = telephone.getLine1Number();
        if (TextUtils.isEmpty(fetchedPhoneNumber)) {
            return phoneNumber;
        }
        try {
            return PhoneNumberUtil.getInstance().parse(fetchedPhoneNumber, Locale.getDefault().getCountry());
        } catch (NumberParseException e) {
            Log.e(TAG, "Error parsing number");
            return phoneNumber;
        }
    }

    public String getSimCountryCodeUppercase() {
        return ((TelephonyManager) this.context.getSystemService("phone")).getSimCountryIso().toUpperCase();
    }

    public String getCallingCode(String countryCode) {
        String[] callingCodeToCountryCodeMapping;
        if (TextUtils.isEmpty(countryCode) || isUnknownCountry(countryCode)) {
            countryCode = Locale.getDefault().getCountry();
        }
        for (String callingCodeToCountryCode : this.context.getResources().getStringArray(C0716R.array.country_codes)) {
            if (callingCodeToCountryCode.split(",")[1].equals(countryCode)) {
                return callingCodeToCountryCode.split(",")[0];
            }
        }
        BugsnagWrapper.notify(new Throwable("Missing calling code to country code mapping in R.arrays.country_codes: " + countryCode));
        return null;
    }

    private static boolean isUnknownCountry(String countryCode) {
        return "XX".equals(countryCode);
    }

    public static String addLeadingPlusSignIfNecessary(String text) {
        if (!text.startsWith("+")) {
            return "+" + text;
        }
        return text;
    }

    public static String removeLeadingPlusSignIfNecessary(String text) {
        return text.replace("+", "");
    }

    public static boolean isPNSignupEnabled() {
        return BuildHelper.isDebugFeaturesEnabled() || ChinaUtils.isUserInChinaOrPrefersChineseLanguage();
    }

    public static boolean isPNLoginEnabled() {
        if (BuildHelper.isDebugFeaturesEnabled() || ChinaUtils.isUserInChinaOrPrefersChineseLanguage()) {
            return true;
        }
        return Trebuchet.launch(TrebuchetKeys.PHONE_NUMBER_REGISTRATION_ENABLED);
    }

    public static void showPhoneNumberActionSheet(Context context2, String phoneNumber) {
        OptionsMenuFactory.forItems(context2, (T[]) CONTACT_US_ACTIONS).setTitleTransformer(PhoneUtil$$Lambda$1.lambdaFactory$(context2, phoneNumber)).setListener(PhoneUtil$$Lambda$2.lambdaFactory$(context2, phoneNumber)).buildAndShow();
    }

    /* access modifiers changed from: private */
    public static String actionTitle(Context context2, int action, String phoneNumber) {
        if (action == 0) {
            return context2.getString(C0716R.string.call_support_phone_number, new Object[]{phoneNumber});
        } else if (action == 1) {
            return context2.getString(C0716R.string.copy_phone_number, new Object[]{phoneNumber});
        } else {
            throw new RuntimeException("Unexpected contact us action: " + action);
        }
    }

    /* access modifiers changed from: private */
    public static void actionSelected(Context context2, int action, String phoneNumber) {
        if (action == 0) {
            CallHelper.dial(context2, phoneNumber);
        } else if (action == 1) {
            MiscUtils.copyToClipboard(context2, phoneNumber);
        } else {
            throw new RuntimeException("Unexpected contact us action: " + action);
        }
    }
}
