package com.airbnb.android;

import android.app.Application;
import com.airbnb.android.core.net.OkHttpInitializer;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.StethoInitializer;

public class InstrumentationAirbnbApplication extends AirbnbApplication {
    public InstrumentationAirbnbApplication(Application application, OkHttpInitializer okHttpInitializer, StethoInitializer stethoInitializer) {
        super(application, okHttpInitializer, stethoInitializer);
    }
}
