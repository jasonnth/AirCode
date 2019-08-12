package com.airbnb.android.apprater;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.core.utils.DebugSettings;

public class AppRaterModule {
    @ChildScope
    public AppRaterController provideAppRaterController(DebugSettings debugSettings, AirbnbPreferences airbnbPreferences) {
        return new AppRaterController(debugSettings, airbnbPreferences);
    }
}
