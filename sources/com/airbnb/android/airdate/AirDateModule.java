package com.airbnb.android.airdate;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class AirDateModule extends SimpleModule {
    public AirDateModule() {
        super(new Version(1, 0, 0, "alpha"));
        addDeserializer(AirDate.class, new AirDateDeserializer());
        addDeserializer(AirDateTime.class, new AirDateTimeDeserializer());
        addSerializer(AirDate.class, new AirDateSerializer());
        addSerializer(AirDateTime.class, new AirDateTimeSerializer());
    }

    public String getModuleName() {
        return getClass().getSimpleName();
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean equals(Object o) {
        return this == o;
    }
}
