package com.airbnb.android.react;

import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.events.AuthStateEvent;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.utils.AirbnbConstants;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.google.common.collect.ImmutableMap;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Locale;
import java.util.Map;

class AccountModule extends VersionedReactModuleBase {
    private static final int VERSION = 1;
    private final AirbnbAccountManager accountManager;
    private final CurrencyFormatter currencyFormatter;

    AccountModule(ReactApplicationContext reactContext, AirbnbAccountManager accountManager2, CurrencyFormatter currencyFormatter2, Bus bus) {
        super(reactContext, 1);
        this.accountManager = accountManager2;
        this.currencyFormatter = currencyFormatter2;
        new Handler(Looper.getMainLooper()).post(AccountModule$$Lambda$1.lambdaFactory$(this, bus));
    }

    public String getName() {
        return "AccountBridge";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = super.getConstants();
        Map<String, Object> data = getData();
        constants.put("initialAccount", data.get("account"));
        constants.put("localeLanguage", data.get("localeLanguage"));
        constants.put("localeCountry", data.get("localeCountry"));
        constants.put("systemAirbnbLocale", data.get("systemAirbnbLocale"));
        return constants;
    }

    private Map<String, Object> getData() {
        Locale locale = Locale.getDefault();
        return ImmutableMap.m1296of("account", getAccountData(), "localeLanguage", locale.getLanguage(), "localeCountry", locale.getCountry(), "systemAirbnbLocale", locale.toString().replace("_", "-"));
    }

    private Map<String, Object> getAccountData() {
        String country;
        Map<String, Object> data = new ArrayMap<>();
        User user = this.accountManager.getCurrentUser();
        Locale locale = Locale.getDefault();
        data.put(AirbnbConstants.PREFS_CURRENCY, this.currencyFormatter.getLocalCurrencyString());
        data.put("isVRPlatformPoweredHost", Boolean.valueOf(this.accountManager.isVRPlatformPoweredHost()));
        data.put(AccountKitGraphConstants.PARAMETER_LOCALE, locale.getLanguage());
        Map<String, Object> userData = new ArrayMap<>();
        if (user != null) {
            userData.put("id", Double.valueOf(Long.valueOf(user.getId()).doubleValue()));
            userData.put("firstName", user.getFirstName());
            userData.put("lastName", user.getLastName());
            userData.put("email", user.getEmailAddress());
            userData.put("location", user.getLocation());
            userData.put("timezone", user.getTimezone());
            userData.put("isHost", Boolean.valueOf(user.isHost()));
            userData.put("isSuperhost", Boolean.valueOf(user.isSuperhost()));
            userData.put("pictureUrl", user.getPictureUrl());
            userData.put("pictureLargeUrl", user.getPictureUrlLarge());
            userData.put("hasProfilePic", Boolean.valueOf(user.hasProfilePic()));
            userData.put("totalListingsCount", Integer.valueOf(user.getTotalListingsCount()));
            userData.put("listingsCount", Integer.valueOf(user.getListingsCount()));
            String str = "country";
            if (TextUtils.isEmpty(user.getCountry())) {
                country = user.getCountryOfResidence();
            } else {
                country = user.getCountry();
            }
            userData.put(str, country);
            String birthday = "";
            AirDate birthDate = user.getBirthdate();
            if (birthDate != null) {
                birthday = birthDate.getIsoDateString();
            }
            userData.put("birthdate", birthday);
            userData.put("createdAt", user.getCreatedAt().getIsoDateStringUTC());
            userData.put("languages", user.getLanguages());
        }
        data.put("user", userData);
        return data;
    }

    @Subscribe
    public void loginStatusChanged(AuthStateEvent lse) {
        ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), "airbnb.accountUpdated", ConversionUtil.toWritableMap(getData()));
    }

    @ReactMethod
    public void logout() {
        ((ReactNativeActivity) getReactApplicationContext().getCurrentActivity()).logout();
    }
}
