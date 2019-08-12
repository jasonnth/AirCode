package com.airbnb.android.checkin.data;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.checkin.CheckInGuideDataModel;
import com.airbnb.android.checkin.CheckInGuideDataModel.Creator;
import com.airbnb.android.core.models.CheckInGuide;

final /* synthetic */ class CheckInGuideData$$Lambda$1 implements Creator {
    private static final CheckInGuideData$$Lambda$1 instance = new CheckInGuideData$$Lambda$1();

    private CheckInGuideData$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public CheckInGuideDataModel create(long j, AirDateTime airDateTime, CheckInGuide checkInGuide) {
        return new AutoValue_CheckInGuideData(j, airDateTime, checkInGuide);
    }
}
