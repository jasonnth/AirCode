package com.airbnb.android.core.data;

import com.airbnb.android.aireventlogger.AirEvent;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class AirEventModule extends SimpleModule {
    public AirEventModule() {
        addSerializer(AirEvent.class, new AirEventSerializer());
    }
}
