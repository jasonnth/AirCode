package com.miteksystems.misnap.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.miteksystems.misnap.params.SDKConstants;

public class PreferenceManager {
    String mSharedPrefsName;

    public PreferenceManager(int i) {
        if (i == 1) {
            this.mSharedPrefsName = SDKConstants.MISNAP_PREFS_NAME_FRONT_CAMERA;
        } else {
            this.mSharedPrefsName = SDKConstants.MISNAP_PREFS_NAME_BACK_CAMERA;
        }
    }

    public boolean save(Context context, String str, String str2) {
        if (context == null || str == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.mSharedPrefsName, 0);
        if (sharedPreferences == null) {
            return false;
        }
        Editor edit = sharedPreferences.edit();
        if (edit == null) {
            return false;
        }
        edit.putString(str, str2);
        return edit.commit();
    }

    public boolean save(Context context, String str, boolean z) {
        if (context == null || str == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.mSharedPrefsName, 0);
        if (sharedPreferences == null) {
            return false;
        }
        Editor edit = sharedPreferences.edit();
        if (edit == null) {
            return false;
        }
        edit.putBoolean(str, z);
        return edit.commit();
    }

    public boolean save(Context context, String str, int i) {
        if (context == null || str == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.mSharedPrefsName, 0);
        if (sharedPreferences == null) {
            return false;
        }
        Editor edit = sharedPreferences.edit();
        if (edit == null) {
            return false;
        }
        edit.putInt(str, i);
        return edit.commit();
    }

    public String getString(Context context, String str) {
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.mSharedPrefsName, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(str, null);
        }
        return null;
    }

    public boolean getBoolean(Context context, String str) {
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.mSharedPrefsName, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(str, false);
        }
        return false;
    }

    public int getInt(Context context, String str) {
        if (context == null) {
            return -1;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.mSharedPrefsName, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(str, -1);
        }
        return -1;
    }
}
