package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.facebook.react.C3704R;

public class DevSettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(C3704R.string.catalyst_settings_title);
        addPreferencesFromResource(C3704R.xml.preferences);
    }
}
