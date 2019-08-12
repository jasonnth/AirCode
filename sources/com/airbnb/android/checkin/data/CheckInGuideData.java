package com.airbnb.android.checkin.data;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.checkin.CheckInGuideDataModel;
import com.airbnb.android.checkin.CheckInGuideDataModel.Factory;
import com.airbnb.android.core.column_adapters.AirDateTimeColumnAdapter;
import com.airbnb.android.core.column_adapters.JsonColumnAdapter;
import com.airbnb.android.core.models.CheckInGuide;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;

public abstract class CheckInGuideData implements Parcelable, CheckInGuideDataModel {
    public static final Factory<CheckInGuideData> FACTORY = new Factory<>(CheckInGuideData$$Lambda$1.lambdaFactory$(), AirDateTimeColumnAdapter.INSTANCE, GUIDE_COLUMN_ADAPTER);
    public static final ColumnAdapter<CheckInGuide, byte[]> GUIDE_COLUMN_ADAPTER = new JsonColumnAdapter(CheckInGuide.class);
    public static final RowMapper<CheckInGuideData> MAPPER = FACTORY.select_allMapper();

    public static CheckInGuideData create(long listingId, AirDateTime updateAt, CheckInGuide guide) {
        return new AutoValue_CheckInGuideData(listingId, updateAt, guide);
    }
}
