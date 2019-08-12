package com.airbnb.android.fixit.fragments;

import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.fixit.FixItReportController.Listener;
import com.airbnb.android.fixit.activities.FixItReportActivity;

final /* synthetic */ class FixItReportFragment$$Lambda$1 implements Listener {
    private final FixItReportActivity arg$1;

    private FixItReportFragment$$Lambda$1(FixItReportActivity fixItReportActivity) {
        this.arg$1 = fixItReportActivity;
    }

    public static Listener lambdaFactory$(FixItReportActivity fixItReportActivity) {
        return new FixItReportFragment$$Lambda$1(fixItReportActivity);
    }

    public void fixItItem(FixItItem fixItItem) {
        this.arg$1.showItem(fixItItem);
    }
}
