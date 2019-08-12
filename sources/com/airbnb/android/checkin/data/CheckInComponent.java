package com.airbnb.android.checkin.data;

import com.airbnb.android.checkin.CheckInIntroFragment;
import com.airbnb.android.checkin.CheckinStepFragment;
import com.airbnb.android.checkin.CheckinStepPagerFragment;
import com.airbnb.android.checkin.ImageViewerActivity;
import com.airbnb.android.checkin.ViewCheckinActivity;
import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface CheckInComponent extends BaseGraph {

    public interface Builder {
        CheckInComponent build();
    }

    void inject(CheckInIntroFragment checkInIntroFragment);

    void inject(CheckinStepFragment checkinStepFragment);

    void inject(CheckinStepPagerFragment checkinStepPagerFragment);

    void inject(ImageViewerActivity imageViewerActivity);

    void inject(ViewCheckinActivity viewCheckinActivity);

    void inject(CheckInDataSyncService checkInDataSyncService);
}
