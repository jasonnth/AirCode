package com.airbnb.android.managelisting.settings.utils;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.managelisting.settings.ManageListingActionExecutor;

final /* synthetic */ class CalendarRowUtils$$Lambda$2 implements OnClickListener {
    private final ManageListingActionExecutor arg$1;

    private CalendarRowUtils$$Lambda$2(ManageListingActionExecutor manageListingActionExecutor) {
        this.arg$1 = manageListingActionExecutor;
    }

    public static OnClickListener lambdaFactory$(ManageListingActionExecutor manageListingActionExecutor) {
        return new CalendarRowUtils$$Lambda$2(manageListingActionExecutor);
    }

    public void onClick(View view) {
        this.arg$1.calendarTip();
    }
}
