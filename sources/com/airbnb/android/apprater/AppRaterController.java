package com.airbnb.android.apprater;

import android.content.SharedPreferences;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;

public final class AppRaterController {
    private static final int DAYS_BEFORE_REMINDING = 3;
    private static final int EVENT_COUNT_TO_RATE = 15;
    private static final String PREF_DATE_REMINDER_PRESSED = "date_reminder_pressed";
    private static final String PREF_KEY_DONT_SHOW = "pref_key_dont_show";
    private static final String PREF_KEY_EVENT_COUNT = "pref_key_event_count";
    private final DebugSettings debugSettings;
    private final SharedPreferences sharedPreferences;

    AppRaterController(DebugSettings debugSettings2, AirbnbPreferences airbnbPreferences) {
        this.debugSettings = debugSettings2;
        this.sharedPreferences = airbnbPreferences.getSharedPreferences();
    }

    public void promptToRateAppIfNeeded(FragmentManager fragmentManager) {
        if (qualifiesForExperiment() && !this.sharedPreferences.getBoolean(PREF_KEY_DONT_SHOW, false)) {
            long remindPressedTimeMS = this.sharedPreferences.getLong(PREF_DATE_REMINDER_PRESSED, 0);
            if (remindPressedTimeMS > 0) {
                if (okayToRemind(remindPressedTimeMS)) {
                    promptToRateWithErfCheck(fragmentManager);
                }
            } else if (this.sharedPreferences.getInt(PREF_KEY_EVENT_COUNT, 0) >= 15) {
                promptToRateWithErfCheck(fragmentManager);
            }
        }
    }

    public int incrementSignificantEvent() {
        return incrementSignificantEvent(this.sharedPreferences);
    }

    public static int incrementSignificantEvent(SharedPreferences sharedPreferences2) {
        int eventCount = sharedPreferences2.getInt(PREF_KEY_EVENT_COUNT, 0) + 1;
        sharedPreferences2.edit().putInt(PREF_KEY_EVENT_COUNT, eventCount).apply();
        return eventCount;
    }

    public void notifyRateAppClicked() {
        this.sharedPreferences.edit().putBoolean(PREF_KEY_DONT_SHOW, true).apply();
    }

    public void notifyRatingRejected() {
        this.sharedPreferences.edit().putBoolean(PREF_KEY_DONT_SHOW, true).apply();
    }

    public void notifyRemindMeClicked() {
        this.sharedPreferences.edit().putLong(PREF_DATE_REMINDER_PRESSED, System.currentTimeMillis()).apply();
    }

    private boolean qualifiesForExperiment() {
        if (!BuildHelper.isChina() || Trebuchet.launch(TrebuchetKeys.APP_RATER_KILLSWITCH, false)) {
            if (!BuildHelper.isDebugFeaturesEnabled()) {
                return false;
            }
            DebugSettings debugSettings2 = this.debugSettings;
            if (!DebugSettings.APP_RATER.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private boolean okayToRemind(long remindPressedTimeMS) {
        return System.currentTimeMillis() - remindPressedTimeMS >= 259200000;
    }

    private void promptToRateWithErfCheck(FragmentManager fragmentManager) {
        new AppRaterDialogFragment().show(fragmentManager, "app_rater");
    }
}
