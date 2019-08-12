package com.airbnb.android.itinerary.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.TimelineMetadata;
import java.util.List;

final /* synthetic */ class TimelineFragment$$Lambda$2 implements OnClickListener {
    private final TimelineFragment arg$1;
    private final List arg$2;
    private final TimelineMetadata arg$3;

    private TimelineFragment$$Lambda$2(TimelineFragment timelineFragment, List list, TimelineMetadata timelineMetadata) {
        this.arg$1 = timelineFragment;
        this.arg$2 = list;
        this.arg$3 = timelineMetadata;
    }

    public static OnClickListener lambdaFactory$(TimelineFragment timelineFragment, List list, TimelineMetadata timelineMetadata) {
        return new TimelineFragment$$Lambda$2(timelineFragment, list, timelineMetadata);
    }

    public void onClick(View view) {
        this.arg$1.itineraryNavigationController.navigateToPendingScreen(this.arg$2, this.arg$3);
    }
}
