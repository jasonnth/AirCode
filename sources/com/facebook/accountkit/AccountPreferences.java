package com.facebook.accountkit;

import java.util.Map;

public interface AccountPreferences {

    public interface OnDeletePreferenceListener {
        void onDeletePreference(String str, AccountKitError accountKitError);
    }

    public interface OnLoadPreferenceListener {
        void onLoadPreference(String str, String str2, AccountKitError accountKitError);
    }

    public interface OnLoadPreferencesListener {
        void onLoadPreferences(Map<String, String> map, AccountKitError accountKitError);
    }

    public interface OnSetPreferenceListener {
        void onSetPreference(String str, String str2, AccountKitError accountKitError);
    }

    void deletePreference(String str, OnDeletePreferenceListener onDeletePreferenceListener);

    void loadPreference(String str, OnLoadPreferenceListener onLoadPreferenceListener);

    void loadPreferences(OnLoadPreferencesListener onLoadPreferencesListener);

    void setPreference(String str, String str2, OnSetPreferenceListener onSetPreferenceListener);
}
