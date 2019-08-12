package com.airbnb.android.fixit;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FixItItem;

final /* synthetic */ class FixItReportController$$Lambda$1 implements OnClickListener {
    private final FixItReportController arg$1;
    private final FixItItem arg$2;

    private FixItReportController$$Lambda$1(FixItReportController fixItReportController, FixItItem fixItItem) {
        this.arg$1 = fixItReportController;
        this.arg$2 = fixItItem;
    }

    public static OnClickListener lambdaFactory$(FixItReportController fixItReportController, FixItItem fixItItem) {
        return new FixItReportController$$Lambda$1(fixItReportController, fixItItem);
    }

    public void onClick(View view) {
        this.arg$1.listener.fixItItem(this.arg$2);
    }
}
