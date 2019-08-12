package com.facebook.accountkit.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class ExperimentationConfiguration {
    private static final String AK_PREFERENCES = (PREFERENCE_PREFIX + ".AK_PREFERENCES");
    private static final long DEFAULT_TTL = TimeUnit.DAYS.toMillis(3);
    private static final String PREFERENCE_PREFIX = TAG;
    private static final String PREF_CREATE_TIME = (PREFERENCE_PREFIX + ".PREF_CREATE_TIME");
    private static final String PREF_TTL = (PREFERENCE_PREFIX + ".PREF_TTL");
    private static final String PREF_UNIT_ID = (PREFERENCE_PREFIX + ".PREF_UNIT_ID");
    private static final String TAG = ExperimentationConfiguration.class.getSimpleName();
    private final SharedPreferences mSharedPrefs;

    static void load(Context context, String unitID, Long createTime, Long ttl, Map<Integer, Integer> featureSet) {
        if (unitID != null && createTime != null) {
            saveConfiguration(context, unitID, createTime.longValue(), ttl, featureSet);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private static void saveConfiguration(Context context, String unitID, long createTime, Long ttl, Map<Integer, Integer> featureSet) {
        Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.putLong(PREF_CREATE_TIME, createTime);
        if (ttl != null) {
            editor.putLong(PREF_TTL, ttl.longValue());
        }
        editor.putString(PREF_UNIT_ID, unitID);
        for (Integer prefKey : featureSet.keySet()) {
            editor.putInt(PREFERENCE_PREFIX + prefKey, ((Integer) featureSet.get(prefKey)).intValue());
        }
        editor.commit();
    }

    ExperimentationConfiguration(Context context) {
        this.mSharedPrefs = getSharedPreferences(context);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(AK_PREFERENCES, 0);
    }

    public boolean exists() {
        return this.mSharedPrefs.getLong(PREF_CREATE_TIME, -1) > 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean isStale() {
        long now = Calendar.getInstance().getTime().getTime();
        return Math.abs(now - this.mSharedPrefs.getLong(PREF_CREATE_TIME, now)) > this.mSharedPrefs.getLong(PREF_TTL, DEFAULT_TTL);
    }

    /* access modifiers changed from: 0000 */
    public String getUnitID() {
        return this.mSharedPrefs.getString(PREF_UNIT_ID, null);
    }

    public int getIntValue(Feature feature) {
        return this.mSharedPrefs.getInt(PREFERENCE_PREFIX + feature.getPrefKey(), feature.getDefaultValue());
    }

    public boolean getBooleanValue(Feature feature) {
        return getIntValue(feature) > 0;
    }
}
