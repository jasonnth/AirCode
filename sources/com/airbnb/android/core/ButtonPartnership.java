package com.airbnb.android.core;

import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.ButtonAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.Strap;
import java.lang.ref.WeakReference;

public class ButtonPartnership {
    private static final String BUTTON_DATE_SHARED_PREF_KEY = "button_deeplink_date";
    private static final String BUTTON_URI_SHARED_PREF_KEY = "button_deeplink_uri";
    private static final String TAG = ButtonPartnership.class.getSimpleName();
    private static WeakReference<ButtonPartnership> weakRef = new WeakReference<>(null);
    AirbnbAccountManager accountManager;
    AirbnbPreferences preferences;

    private static final class ButtonUriAndDate {
        final String deeplinkDate;
        final String deeplinkUri;

        public ButtonUriAndDate(String deeplinkUri2, String deeplinkDate2) {
            if (TextUtils.isEmpty(deeplinkUri2) || TextUtils.isEmpty(deeplinkDate2)) {
                throw new IllegalArgumentException("uri and date cannot be empty" + deeplinkUri2 + deeplinkDate2);
            }
            this.deeplinkUri = deeplinkUri2;
            this.deeplinkDate = deeplinkDate2;
        }
    }

    public static ButtonPartnership get() {
        ButtonPartnership button = (ButtonPartnership) weakRef.get();
        if (button != null) {
            return button;
        }
        ButtonPartnership button2 = new ButtonPartnership();
        weakRef = new WeakReference<>(button2);
        return button2;
    }

    private ButtonPartnership() {
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
    }

    public void onOpenDeepLink(String deepLink) {
        if (deepLink != null) {
            Uri uri = Uri.parse(deepLink);
            if (uri != null && !TextUtils.isEmpty(uri.getQueryParameter("btn_ref")) && "airbnb".equals(uri.getScheme())) {
                User currentUser = this.accountManager.getCurrentUser();
                String currentDate = AirDate.today().getIsoDateString();
                if (currentUser != null) {
                    ButtonAnalytics.trackButton(currentDate, currentUser.getId(), deepLink, false);
                } else {
                    saveButtonDeepLink(deepLink, currentDate);
                }
            }
        }
    }

    public void onLogin(User user) {
        ButtonUriAndDate buttonObject = getAndRemoveBtnRef();
        if (buttonObject != null && user != null) {
            ButtonAnalytics.trackButton(buttonObject.deeplinkDate, user.getId(), buttonObject.deeplinkUri, Math.abs(System.currentTimeMillis() - user.getCreatedAt().getMillis()) < 300000);
        }
    }

    private void saveButtonDeepLink(String deeplinkUri, String deeplinkDate) {
        Log.d(TAG, Strap.make().mo11639kv(BUTTON_URI_SHARED_PREF_KEY, deeplinkUri).mo11639kv("deeplink_date", deeplinkDate).mo11639kv("save_for_later", "because_user_not_signed_in").toString());
        this.preferences.getGlobalSharedPreferences().edit().putString(BUTTON_URI_SHARED_PREF_KEY, deeplinkUri).putString(BUTTON_DATE_SHARED_PREF_KEY, deeplinkDate).apply();
    }

    private ButtonUriAndDate getAndRemoveBtnRef() {
        SharedPreferences prefs = this.preferences.getGlobalSharedPreferences();
        if (!prefs.contains(BUTTON_URI_SHARED_PREF_KEY)) {
            return null;
        }
        ButtonUriAndDate rtn = new ButtonUriAndDate(prefs.getString(BUTTON_URI_SHARED_PREF_KEY, null), prefs.getString(BUTTON_DATE_SHARED_PREF_KEY, null));
        prefs.edit().remove(BUTTON_URI_SHARED_PREF_KEY).remove(BUTTON_DATE_SHARED_PREF_KEY).apply();
        return rtn;
    }
}
