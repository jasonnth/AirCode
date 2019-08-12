package com.airbnb.android.core.column_adapters;

import com.airbnb.android.airdate.AirDateTime;
import com.squareup.sqldelight.ColumnAdapter;

public class AirDateTimeColumnAdapter implements ColumnAdapter<AirDateTime, String> {
    public static final AirDateTimeColumnAdapter INSTANCE = new AirDateTimeColumnAdapter();

    public AirDateTime decode(String databaseValue) {
        return databaseValue != null ? AirDateTime.parseWithCurrentTimeZone(databaseValue) : AirDateTime.now();
    }

    public String encode(AirDateTime value) {
        return value.getIsoDateStringUTC();
    }
}
