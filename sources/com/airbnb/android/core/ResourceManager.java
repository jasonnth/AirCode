package com.airbnb.android.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.models.Mario;
import com.airbnb.android.core.requests.MarioExperimentRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.LanguageUtils;
import com.google.gson.jpush.Gson;
import java.util.Collections;
import java.util.List;

public final class ResourceManager {
    private static String DEVICE_PREVIOUS_LANGUAGE = "USER_PREVIOUS_LANGUAGE";
    private static String TAG = ResourceManager.class.getSimpleName();
    AirbnbAccountManager accountManager;
    private final Context context;
    ErfAnalytics erfAnalytics;
    AirbnbPreferences preferences;

    public ResourceManager(Context context2) {
        this.context = context2;
        ((CoreGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    public String getString(int stringRes) {
        String stringName = this.context.getResources().getResourceEntryName(stringRes);
        String res = getStringResourceByName(stringName);
        Mario mario = getMarioWithKey(stringName);
        if (!TextUtils.isEmpty(mario.getValue())) {
            res = mario.getValue();
            logExperimentImpression(stringName);
        }
        if (TextUtils.isEmpty(mario.getValue())) {
            return res;
        }
        return mario.getValue();
    }

    private Mario getMarioWithKey(String marioKey) {
        Mario mario = new Mario();
        String marioGson = this.preferences.getDeviceOrUserPreferences().getString(marioKey, "");
        if (!TextUtils.isEmpty(marioGson)) {
            return (Mario) new Gson().fromJson(marioGson, Mario.class);
        }
        return mario;
    }

    private String getStringResourceByName(String key) {
        return this.context.getString(this.context.getResources().getIdentifier(key, "string", this.context.getPackageName()));
    }

    public void storeMario(List<Mario> marios) {
        for (Mario mario : marios) {
            this.preferences.getDeviceOrUserPreferences().edit().putString(mario.getParameterName(), new Gson().toJson((Object) mario)).apply();
        }
    }

    private void logExperimentImpression(String marioKey) {
        if (this.accountManager.isCurrentUserAuthorized()) {
            logUserExperimentImpression(marioKey);
        } else {
            logBevExperimentImpression(marioKey);
        }
    }

    private void logBevExperimentImpression(String marioKey) {
        Mario mario = getMarioWithKey(marioKey);
        if (!TextUtils.isEmpty(mario.getExperiment_name())) {
            this.erfAnalytics.logExperiment(new ErfExperiment(mario.getExperiment_name(), mario.getAssigned_treatment()), mario.getAssigned_treatment());
        }
    }

    private void logUserExperimentImpression(String marioKey) {
        Mario mario = getMarioWithKey(marioKey);
        if (!TextUtils.isEmpty(mario.getExperiment_name())) {
            this.erfAnalytics.logExperiment(new ErfExperiment(mario.getExperiment_name(), mario.getAssigned_treatment(), Collections.emptyList(), "user", 0, 0), mario.getAssigned_treatment());
        }
    }

    public void fetchResourceIfLanguageChanged() {
        String currentLanguage = LanguageUtils.getLanguage();
        String previousLanguage = this.preferences.getGlobalSharedPreferences().getString(DEVICE_PREVIOUS_LANGUAGE, "");
        if (!TextUtils.equals(currentLanguage, previousLanguage)) {
            this.preferences.getGlobalSharedPreferences().edit().putString(DEVICE_PREVIOUS_LANGUAGE, currentLanguage).apply();
            if (!TextUtils.isEmpty(previousLanguage)) {
                MarioExperimentRequest.newRequest().execute(NetworkUtil.singleFireExecutor());
                Log.d(TAG, "Language swtich is detected, fetching mario to flush the cache");
            }
        }
    }
}
